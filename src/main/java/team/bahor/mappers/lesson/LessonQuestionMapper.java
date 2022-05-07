package team.bahor.mappers.lesson;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import team.bahor.dto.lesson.question.LessonQuestionCreateDto;
import team.bahor.dto.lesson.question.LessonQuestionDto;
import team.bahor.dto.lesson.question.LessonQuestionUpdateDto;
import team.bahor.entity.lesson.LessonQuestion;
import team.bahor.mappers.base.AbstractMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LessonQuestionMapper extends AbstractMapper<
        LessonQuestion,
        LessonQuestionDto,
        LessonQuestionCreateDto,
        LessonQuestionUpdateDto> {
    @Override
    LessonQuestionDto toDto(LessonQuestion entity);

    @Override
    List<LessonQuestionDto> toDto(List<LessonQuestion> entities);

    @Override
    LessonQuestion fromCreateDto(LessonQuestionCreateDto createDto);

    @Override
    LessonQuestion fromUpdateDto(LessonQuestionUpdateDto updateDto);

    LessonQuestion fromUpdateDto(LessonQuestionUpdateDto updateDto, @MappingTarget LessonQuestion entity);
}
