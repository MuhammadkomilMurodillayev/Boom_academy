package team.bahor.services.lesson;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team.bahor.dto.lesson.LessonCreateDto;
import team.bahor.entity.lesson.Lesson;
import team.bahor.mappers.lesson.LessonMapper;
import team.bahor.repositories.lesson.LessonRepository;
import team.bahor.services.base.AbstractService;
import team.bahor.services.file.FileStorageService;
import team.bahor.utils.Utils;
import team.bahor.validators.lesson.LessonValidator;

import java.util.UUID;

@Service
public class LessonServiceImpl extends AbstractService<LessonRepository, LessonMapper, LessonValidator> {

    protected LessonServiceImpl(LessonMapper mapper, LessonValidator validator, LessonRepository repository, FileStorageService fileStorageService) {
        super(mapper, validator, repository);
        this.fileStorageService = fileStorageService;
    }

    private final FileStorageService fileStorageService;

    public String create(LessonCreateDto dto, MultipartFile file) {
        validator.validOnCreate(dto);

        Lesson lesson = mapper.fromCreateDto(dto);

        lesson.setId(UUID.randomUUID().toString());
        String videoPath = fileStorageService.uploadVideoFile(file, lesson.getSectionId());
        lesson.setVideoUrl(videoPath);
        lesson.setCreatedBy(Utils.getSessionId());
        lesson.setPosition(getPosition(dto));

        // TODO: 3/28/22 save file of the lesson
        return null;
    }

    private Short getPosition(LessonCreateDto dto) {
        return null;
    }
}
