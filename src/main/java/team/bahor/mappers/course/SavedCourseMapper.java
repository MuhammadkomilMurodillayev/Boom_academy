package team.bahor.mappers.course;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import team.bahor.dto.course.saved.SavedCourseCreateDto;
import team.bahor.dto.course.saved.SavedCourseDto;
import team.bahor.dto.course.saved.SavedCourseUpdateDto;
import team.bahor.entity.courses.SavedCourse;
import team.bahor.mappers.base.AbstractMapper;

import java.util.List;


@Component
@Mapper(componentModel = "spring")
public interface SavedCourseMapper extends AbstractMapper<
        SavedCourse,
        SavedCourseDto,
        SavedCourseCreateDto,
        SavedCourseUpdateDto> {

    @Override
    SavedCourseDto toDto(SavedCourse entity);

    @Override
    List<SavedCourseDto> toDto(List<SavedCourse> entities);

    @Override
    SavedCourse fromCreateDto(SavedCourseCreateDto createDto);

    @Override
    SavedCourse fromUpdateDto(SavedCourseUpdateDto updateDto);
}
