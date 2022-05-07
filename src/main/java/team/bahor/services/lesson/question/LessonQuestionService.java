package team.bahor.services.lesson.question;

import team.bahor.dto.lesson.question.LessonQuestionCreateDto;
import team.bahor.dto.lesson.question.LessonQuestionDto;
import team.bahor.dto.lesson.question.LessonQuestionUpdateDto;
import team.bahor.services.base.GenericCrudService;

public interface LessonQuestionService extends GenericCrudService<
        LessonQuestionDto,
        LessonQuestionCreateDto,
        LessonQuestionUpdateDto,
        String> {
}
