package team.bahor.services.course.saved;

import team.bahor.dto.course.saved.SavedCourseCreateDto;
import team.bahor.dto.course.saved.SavedCourseDto;
import team.bahor.dto.course.saved.SavedCourseUpdateDto;
import team.bahor.services.base.GenericCrudService;

public interface SavedCourseService extends GenericCrudService<
        SavedCourseDto,
        SavedCourseCreateDto,
        SavedCourseUpdateDto,
        String> {
}
