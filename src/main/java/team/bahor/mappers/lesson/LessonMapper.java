package team.bahor.mappers.lesson;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import team.bahor.dto.lesson.LessonCreateDto;
import team.bahor.dto.lesson.LessonDto;
import team.bahor.dto.lesson.LessonUpdateDto;
import team.bahor.entity.lesson.Lesson;
import team.bahor.mappers.base.AbstractMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LessonMapper extends AbstractMapper<
        Lesson,
        LessonDto,
        LessonCreateDto,
        LessonUpdateDto> {
    @Override
    LessonDto toDto(Lesson entity);

    @Override
    List<LessonDto> toDto(List<Lesson> entities);

    @Override
    Lesson fromCreateDto(LessonCreateDto createDto);

    @Override
    Lesson fromUpdateDto(LessonUpdateDto updateDto);
}
