package team.bahor.mappers.course;

import org.mapstruct.*;
import org.springframework.stereotype.Component;
import team.bahor.dto.course.CourseCreateDto;
import team.bahor.dto.course.CourseDto;
import team.bahor.dto.course.CourseUpdateDto;
import team.bahor.entity.courses.Course;
import team.bahor.mappers.base.AbstractMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring",  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CourseMapper extends AbstractMapper<
        Course,
        CourseDto,
        CourseCreateDto,
        CourseUpdateDto> {

    @Override
    CourseDto toDto(Course entity);

    @Override
    List<CourseDto> toDto(List<Course> entities);

    @Override
    @Mapping(target = "category", ignore = true)
    Course fromCreateDto(CourseCreateDto createDto);

    @Override
    Course fromUpdateDto(CourseUpdateDto updateDto);

    Course fromUpdateDto(CourseUpdateDto updateDto, @MappingTarget Course course);
}
