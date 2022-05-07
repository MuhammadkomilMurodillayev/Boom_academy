package team.bahor.services.exam.examQuestionGeneration;

import team.bahor.dto.exam.examQuestionGeneration.ExamQuestionGenerationCreateDto;
import team.bahor.dto.exam.examQuestionGeneration.ExamQuestionGenerationDto;
import team.bahor.dto.exam.examQuestionGeneration.ExamQuestionGenerationUpdateDto;
import team.bahor.entity.exam.ExamQuestionGeneration;
import team.bahor.services.base.GenericCrudService;

public interface ExamQuestionGenerationService extends GenericCrudService<
        ExamQuestionGenerationDto,
        ExamQuestionGenerationCreateDto,
        ExamQuestionGenerationUpdateDto,
        String
        > {

}
