package team.bahor.mappers.exam;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import team.bahor.dto.exam.examQuestion.ExamQuestionCreateDto;
import team.bahor.dto.exam.examQuestion.ExamQuestionDto;
import team.bahor.dto.exam.examQuestion.ExamQuestionUpdateDto;
import team.bahor.entity.exam.ExamQuestion;
import team.bahor.mappers.base.AbstractMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ExamQuestionMapper extends AbstractMapper<
        ExamQuestion,
        ExamQuestionDto,
        ExamQuestionCreateDto,
        ExamQuestionUpdateDto
        > {
    //    @Override
//    @Mapping(target = "answers",ignore = true)
//    @Mapping(target = "type",ignore = true)
    ExamQuestionDto toDto(ExamQuestion entity);

    @Override
    List<ExamQuestionDto> toDto(List<ExamQuestion> entities);

    @Override
    @Mapping(target = "type", ignore = true)
    ExamQuestion fromCreateDto(ExamQuestionCreateDto createDto);

    @Override
    ExamQuestion fromUpdateDto(ExamQuestionUpdateDto updateDto);

    ExamQuestion fromUpdateDto(ExamQuestionUpdateDto updateDto,@MappingTarget ExamQuestion examQuestion);
}
