package team.bahor.services.course;

import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import team.bahor.dto.course.CourseCreateDto;
import team.bahor.dto.course.CourseDto;
import team.bahor.dto.course.CourseUpdateDto;
import team.bahor.entity.courses.Course;
import team.bahor.enums.CourseCategory;
import team.bahor.enums.Role;
import team.bahor.mappers.course.CourseMapper;
import team.bahor.properties.CourseProperties;
import team.bahor.repositories.course.CourseRepository;
import team.bahor.services.base.AbstractService;
import team.bahor.services.base.GenericCrudService;
import team.bahor.utils.Utils;
import team.bahor.validators.course.CourseValidator;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CourseService extends AbstractService<
        CourseRepository,
        CourseMapper,
        CourseValidator>
        implements GenericCrudService<
        CourseDto,
        CourseCreateDto,
        CourseUpdateDto,
        String> {

    private final CourseProperties properties;

    protected CourseService(@Qualifier("courseMapperImpl") CourseMapper mapper, CourseValidator validator, CourseRepository repository, CourseProperties properties) {
        super(mapper, validator, repository);
        this.properties = properties;
    }

    @Override
    public String create(CourseCreateDto createDto) {

        validator.validOnCreate(createDto);
        Course course = mapper.fromCreateDto(createDto);

        course.setCategory(CourseCategory.valueOf(createDto.getCategory()));
        course.setId(UUID.randomUUID().toString().replace("-", ""));
        course.setCreatedBy(Utils.getSessionId());
        course.setStatus(properties.getNonActiveStatus());
        course.setRatingCount(0);
        course.setRating(0);
        course = repository.save(course);
        return course.getId();

    }

    @Override
    public void delete(String id) {

        validator.validateKey(id);
        Optional<Course> courseOptional = repository.findByIdAndDeletedFalse(id);
        Course course = courseOptional.get();
        course.setDeleted(true);
        repository.save(course);

    }

    @Override
    public void update(CourseUpdateDto updateDto) {

        validator.validOnUpdate(updateDto);
        Optional<Course> courseOptional = repository.findByIdAndDeletedFalse(updateDto.getId());
        Course course = courseOptional.get();
        validator.validateKey(updateDto.getId());
        mapper.fromUpdateDto(updateDto, course);

    }

    @Override
    public CourseDto get(String id) {

        validator.validateKey(id);
        Optional<Course> courseOptional = repository.findByIdAndDeletedFalse(id);
        Course course = courseOptional.get();
        return mapper.toDto(course);

    }

    @Override
    public List<CourseDto> getAll() {

        validator.validateKey(null);
        List<Course> courses = repository.findAllByDeletedFalse();
        return mapper.toDto(courses);

    }

    public List<CourseDto> getActiveCourses() {

        validator.validateKey(null);
        List<Course> courses = repository.findAllByStatusAndDeletedFalse(properties.getActiveStatus());
        return mapper.toDto(courses);

    }

    public List<CourseDto> getNonActiveCourses() {
        validator.validPermission(Role.MANAGER.name(), Role.SUPER_ADMIN.name(), Role.ADMIN.name());
        validator.validateKey(null);
        List<Course> courses = repository.findAllByStatusAndDeletedFalse(properties.getNonActiveStatus());

        return mapper.toDto(courses);

    }

    public List<CourseDto> getMyCourses() {
        List<Course> courses = repository.findAllByCreatedByAndDeletedFalse(Utils.getSessionId());
        return mapper.toDto(courses);
    }

    public List<CourseDto> getMyActiveCourses() {
        List<Course> courses = repository.findAllByCreatedByAndStatusAndDeletedFalse(Utils.getSessionId(), properties.getActiveStatus());
        return mapper.toDto(courses);
    }

    public List<CourseDto> getMyNonActiveCourses() {
        List<Course> courses = repository.findAllByCreatedByAndStatusAndDeletedFalse(Utils.getSessionId(), properties.getNonActiveStatus());
        return mapper.toDto(courses);
    }


    public void activated(String id) {

        validator.validateKey(id);
        Optional<Course> courseOptional = repository.findByIdAndDeletedFalse(id);

        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            course.setStatus(properties.getActiveStatus());
            repository.save(course);
        }

    }

    public void nonActivated(String id) {

        validator.validateKey(id);
        Optional<Course> courseOptional = repository.findByIdAndDeletedFalse(id);

        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            course.setStatus(properties.getNonActiveStatus());
            repository.save(course);
        }

    }


}
