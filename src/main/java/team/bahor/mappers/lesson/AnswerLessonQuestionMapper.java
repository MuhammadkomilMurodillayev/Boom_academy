package team.bahor.mappers.lesson;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import team.bahor.dto.lesson.answer.AnswerLessonQuestionCreateDto;
import team.bahor.dto.lesson.answer.AnswerLessonQuestionDto;
import team.bahor.dto.lesson.answer.AnswerLessonQuestionUpdateDto;
import team.bahor.entity.lesson.AnswerLessonQuestion;
import team.bahor.mappers.base.AbstractMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AnswerLessonQuestionMapper extends AbstractMapper<
        AnswerLessonQuestion,
        AnswerLessonQuestionDto,
        AnswerLessonQuestionCreateDto,
        AnswerLessonQuestionUpdateDto> {

    @Override
    AnswerLessonQuestionDto toDto(AnswerLessonQuestion entity);

    @Override
    List<AnswerLessonQuestionDto> toDto(List<AnswerLessonQuestion> entities);

    @Override
    AnswerLessonQuestion fromCreateDto(AnswerLessonQuestionCreateDto createDto);

    @Override
    AnswerLessonQuestion fromUpdateDto(AnswerLessonQuestionUpdateDto updateDto);

    AnswerLessonQuestion fromUpdateDto(AnswerLessonQuestionUpdateDto updateDto, @MappingTarget AnswerLessonQuestion entity);
}
