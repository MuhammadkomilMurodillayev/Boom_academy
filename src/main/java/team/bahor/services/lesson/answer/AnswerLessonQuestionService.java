package team.bahor.services.lesson.answer;

import team.bahor.dto.lesson.answer.AnswerLessonQuestionCreateDto;
import team.bahor.dto.lesson.answer.AnswerLessonQuestionDto;
import team.bahor.dto.lesson.answer.AnswerLessonQuestionUpdateDto;
import team.bahor.services.base.GenericCrudService;

public interface AnswerLessonQuestionService extends GenericCrudService<
        AnswerLessonQuestionDto,
        AnswerLessonQuestionCreateDto,
        AnswerLessonQuestionUpdateDto,
        String> {
}
