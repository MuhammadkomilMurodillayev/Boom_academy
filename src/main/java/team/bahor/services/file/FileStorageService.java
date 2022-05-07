package team.bahor.services.file;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import team.bahor.dto.file.FileStorageDto;
import team.bahor.dto.file.FileStorageUpdateDto;
import team.bahor.entity.file.FileStorage;
import team.bahor.exeptions.fileStore.FileStorageException;
import team.bahor.exeptions.fileStore.StorageFileNotFoundException;
import team.bahor.mappers.file.FileStorageMapper;
import team.bahor.properties.FileStorageProperties;
import team.bahor.repositories.file.FileStorageRepository;
import team.bahor.services.base.AbstractService;
import team.bahor.utils.Utils;
import team.bahor.validators.file.FileStorageValidator;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;


@Service
public class FileStorageService extends AbstractService<
        FileStorageRepository,
        FileStorageMapper,
        FileStorageValidator> {

    private final Path rootLocation;

    protected FileStorageService(FileStorageMapper mapper, FileStorageValidator validator, FileStorageRepository repository, FileStorageProperties properties) {
        super(mapper, validator, repository);
        this.rootLocation = Paths.get(properties.getLocation());
    }

    public String create(MultipartFile file, String lessonId) {

        validator.checkFile(file);
        FileStorage fileStorage = uploadAnyFile(file, new FileStorage());
        fileStorage.setId(UUID.randomUUID().toString());
        fileStorage.setLessonId(lessonId);
        fileStorage.setCreatedBy(Utils.getSessionId());

        repository.save(fileStorage);

        return fileStorage.getId();
    }

    public void delete(String generatedName) {

        validator.checkRole(generatedName);
        Optional<FileStorage> fileStorageOptional = repository.findByGeneratedNameAndDeletedFalse(generatedName);
        if (fileStorageOptional.isPresent()) {
            FileStorage fileStorage = fileStorageOptional.get();
            fileStorage.setDeleted(true);
            repository.save(fileStorage);
        }

    }

    public void update(FileStorageUpdateDto updateDto) {

    }

    public FileStorageDto get(String generatedName) {
        Optional<FileStorage> fileStorageOptional = repository.findByGeneratedNameAndDeletedFalse(generatedName);
        Resource resource = fileStorageOptional.map(fileStorage -> loadAsResource(fileStorage.getGeneratedName())).orElse(null);
        FileStorageDto fileStorageDto = mapper.toDto(fileStorageOptional.get());
        fileStorageDto.setFile(resource);

        return fileStorageDto;

    }

    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    public List<FileStorageDto> getAll(String lessonId) {
        List<FileStorage> fileStorages = repository.findAll();
        return mapper.toDto(fileStorages);
    }

    public String uploadVideoFile(MultipartFile multipartFile, String lessonId) {

        validator.checkVideoFile(multipartFile, lessonId);
        FileStorage fileStorage = uploadAnyFile(multipartFile, new FileStorage());
        fileStorage.setId(UUID.randomUUID().toString());
        fileStorage.setLessonId(lessonId);
        repository.save(fileStorage);

        return fileStorage.getPath();

    }

    public FileStorage uploadAnyFile(MultipartFile file, FileStorage fileStorage) {

        String format = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String generatedName = UUID.randomUUID().toString().replace("-", "") + "." + format;
        Path path = Paths.get(rootLocation.toString(), generatedName);

        try {
            Files.copy(file.getInputStream(), path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileStorage.setGeneratedName(generatedName);
        fileStorage.setOriginalName(file.getOriginalFilename());
        String[] type = file.getContentType().split("/");
        fileStorage.setType(type[0]);

        fileStorage.setPath(path.toString());

        return fileStorage;
    }


    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        } catch (IOException e) {
            throw new FileStorageException("Failed to read stored files", e);
        }
    }


    public Stream<Resource> loadAllAsResource() {
        List<Resource> resources = new ArrayList<>();
        Stream<Path> pathStream = loadAll();
        pathStream.forEach(path -> {
            try {
                Resource resource = new UrlResource(path.toUri());
                resources.add(resource);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });

        return resources.stream();

    }

    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new FileStorageException("Could not initialize storage", e);
        }
    }

    public void hardDeleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
        init();
    }
}
