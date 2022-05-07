package team.bahor.services.lesson;

import team.bahor.dto.lesson.LessonCreateDto;
import team.bahor.dto.lesson.LessonDto;
import team.bahor.dto.lesson.LessonUpdateDto;
import team.bahor.services.base.GenericCrudService;

public interface LessonService
        extends GenericCrudService<LessonDto, LessonCreateDto, LessonUpdateDto, String> {
}
