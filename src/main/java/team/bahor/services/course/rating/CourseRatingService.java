package team.bahor.services.course.rating;

import team.bahor.dto.course.rating.CourseRatingCreateDto;
import team.bahor.dto.course.rating.CourseRatingDto;
import team.bahor.dto.course.rating.CourseRatingUpdateDto;
import team.bahor.services.base.GenericCrudService;

public interface CourseRatingService extends GenericCrudService<
        CourseRatingDto,
        CourseRatingCreateDto,
        CourseRatingUpdateDto,
        String> {
}
