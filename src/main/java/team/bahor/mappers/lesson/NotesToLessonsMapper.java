package team.bahor.mappers.lesson;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import team.bahor.dto.lesson.note.NotesToLessonsCreateDto;
import team.bahor.dto.lesson.note.NotesToLessonsDto;
import team.bahor.dto.lesson.note.NotesToLessonsUpdateDto;
import team.bahor.entity.lesson.NotesToLessons;
import team.bahor.mappers.base.AbstractMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NotesToLessonsMapper extends AbstractMapper<NotesToLessons, NotesToLessonsDto, NotesToLessonsCreateDto, NotesToLessonsUpdateDto> {
    @Override
    NotesToLessonsDto toDto(NotesToLessons entity);

    @Override
    List<NotesToLessonsDto> toDto(List<NotesToLessons> entities);

    @Override
    NotesToLessons fromCreateDto(NotesToLessonsCreateDto createDto);

    @Override
    NotesToLessons fromUpdateDto(NotesToLessonsUpdateDto updateDto);

    NotesToLessons fromUpdateDto(NotesToLessonsUpdateDto updateDto, @MappingTarget NotesToLessons entity);
}
