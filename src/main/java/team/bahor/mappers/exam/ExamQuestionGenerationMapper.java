package team.bahor.mappers.exam;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import team.bahor.dto.exam.examQuestionGeneration.ExamQuestionGenerationCreateDto;
import team.bahor.dto.exam.examQuestionGeneration.ExamQuestionGenerationDto;
import team.bahor.dto.exam.examQuestionGeneration.ExamQuestionGenerationUpdateDto;
import team.bahor.entity.exam.ExamQuestionGeneration;
import team.bahor.mappers.base.AbstractMapper;
import team.bahor.mappers.base.BaseGenericMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ExamQuestionGenerationMapper extends AbstractMapper<
        ExamQuestionGeneration,
        ExamQuestionGenerationDto,
        ExamQuestionGenerationCreateDto,
        ExamQuestionGenerationUpdateDto
        > {
    @Override
    List<ExamQuestionGenerationDto> toDto(List<ExamQuestionGeneration> entities);

    @Override
    ExamQuestionGeneration fromCreateDto(ExamQuestionGenerationCreateDto createDto);

    @Override
    ExamQuestionGeneration fromUpdateDto(ExamQuestionGenerationUpdateDto updateDto);

    @Override
    ExamQuestionGenerationDto toDto(ExamQuestionGeneration entity);
}
