package team.bahor.mappers.exam;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import team.bahor.dto.exam.answerToExamQuestion.AnswerToExamQuestionCreateDto;
import team.bahor.dto.exam.answerToExamQuestion.AnswerToExamQuestionDto;
import team.bahor.dto.exam.answerToExamQuestion.AnswerToExamQuestionUpdateDto;
import team.bahor.entity.exam.AnswerToExamQuestion;
import team.bahor.mappers.base.AbstractMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AnswerToExamQuestionMapper extends AbstractMapper<
        AnswerToExamQuestion,
        AnswerToExamQuestionDto,
        AnswerToExamQuestionCreateDto,
        AnswerToExamQuestionUpdateDto
        > {
    @Override
    AnswerToExamQuestionDto toDto(AnswerToExamQuestion entity);

    @Override
    List<AnswerToExamQuestionDto> toDto(List<AnswerToExamQuestion> entities);

    @Override
    AnswerToExamQuestion fromCreateDto(AnswerToExamQuestionCreateDto createDto);

    @Override
    AnswerToExamQuestion fromUpdateDto(AnswerToExamQuestionUpdateDto updateDto);

    AnswerToExamQuestion fromUpdateDto(AnswerToExamQuestionUpdateDto updateDto, @MappingTarget AnswerToExamQuestion answerToExamQuestion);
}
