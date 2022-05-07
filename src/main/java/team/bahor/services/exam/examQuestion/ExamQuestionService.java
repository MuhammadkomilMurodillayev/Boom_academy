package team.bahor.services.exam.examQuestion;

import team.bahor.dto.exam.examQuestion.ExamQuestionCreateDto;
import team.bahor.dto.exam.examQuestion.ExamQuestionDto;
import team.bahor.dto.exam.examQuestion.ExamQuestionUpdateDto;
import team.bahor.entity.exam.ExamQuestion;
import team.bahor.services.base.GenericCrudService;

public interface ExamQuestionService extends GenericCrudService<
        ExamQuestionDto,
        ExamQuestionCreateDto,
        ExamQuestionUpdateDto,
        String
        > {
}
