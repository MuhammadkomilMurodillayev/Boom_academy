package team.bahor.validators.file;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import team.bahor.dto.file.FileStorageDto;
import team.bahor.entity.file.FileStorage;
import team.bahor.enums.Role;
import team.bahor.exeptions.NotAllowedException;
import team.bahor.exeptions.fileStore.FileStorageException;
import team.bahor.repositories.file.FileStorageRepository;
import team.bahor.utils.Utils;
import team.bahor.validators.base.BaseGenericValidator;

import java.util.Objects;
import java.util.Optional;

@Component
public class FileStorageValidator implements BaseGenericValidator {
    private final FileStorageRepository repository;

    public FileStorageValidator(FileStorageRepository repository) {
        this.repository = repository;
    }

    public void checkVideoFile(MultipartFile multipartFile, String lessonId) {
        if (!multipartFile.getContentType().startsWith("video")) {
            throw new FileStorageException("this content invalid, please input video content");
        }
        Optional<FileStorage> fileStorageOptional = repository.findByLessonIdAndTypeAndDeletedFalse(lessonId, "video");

        if (fileStorageOptional.isPresent()) {
            throw new FileStorageException("a video has already been uploaded to this lesson, you can only upload non-video files");
        }
    }

    public void checkFile(MultipartFile file) {
        if (Objects.requireNonNull(file.getContentType()).startsWith("video"))
            throw new NotAllowedException("this content invalid");
    }

    public void checkRole(String generatedName) {

        Optional<FileStorage> fileStorageOptional = repository.findByGeneratedNameAndDeletedAndCreatedBy(generatedName, false, Utils.getSessionId());

        if (fileStorageOptional.isEmpty()) {
            if (!Utils.sessionHasAnyRole(Role.SUPER_ADMIN.name(), Role.MANAGER.name())) {
                throw new NotAllowedException("not allowed");
            }
        }

    }
}
