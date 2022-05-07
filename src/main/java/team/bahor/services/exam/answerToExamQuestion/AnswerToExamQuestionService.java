package team.bahor.services.exam.answerToExamQuestion;

import team.bahor.dto.exam.answerToExamQuestion.AnswerToExamQuestionCreateDto;
import team.bahor.dto.exam.answerToExamQuestion.AnswerToExamQuestionDto;
import team.bahor.dto.exam.answerToExamQuestion.AnswerToExamQuestionUpdateDto;
import team.bahor.services.base.GenericCrudService;

public interface AnswerToExamQuestionService extends GenericCrudService<
        AnswerToExamQuestionDto,
        AnswerToExamQuestionCreateDto,
        AnswerToExamQuestionUpdateDto,
        String
        > {
}
