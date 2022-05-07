package team.bahor.services.course.saved;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import team.bahor.dto.course.saved.SavedCourseCreateDto;
import team.bahor.dto.course.saved.SavedCourseDto;
import team.bahor.dto.course.saved.SavedCourseUpdateDto;
import team.bahor.entity.courses.SavedCourse;
import team.bahor.mappers.course.SavedCourseMapper;
import team.bahor.repositories.course.SavedCourseRepository;
import team.bahor.services.base.AbstractService;
import team.bahor.validators.course.saved.SavedCourseValidator;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class SavedCourseServiceImpl extends AbstractService<
        SavedCourseRepository,
        SavedCourseMapper,
        SavedCourseValidator> implements SavedCourseService {

    protected SavedCourseServiceImpl(@Qualifier("savedCourseMapperImpl") SavedCourseMapper mapper, SavedCourseValidator validator, SavedCourseRepository repository) {
        super(mapper, validator, repository);
    }

    @Override
    public String create(SavedCourseCreateDto createDto) {
        if (validator.validOnCreated(createDto)) {
            delete(createDto.getUserId(), createDto.getCourseId());
            return "Deleted";
        }

        SavedCourse savedCourse = mapper.fromCreateDto(createDto);
        savedCourse.setId(UUID.randomUUID().toString());
        repository.save(savedCourse);
        return "Saved";
    }

    @Override
    public SavedCourseDto get(String id) {
        validator.validateKey(id);
        try {
            return new ObjectMapper().readValue(repository.getByCourseSavedDto(id), SavedCourseDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SavedCourseDto> getAll() {
        return null;
    }

    public List<SavedCourseDto> getAllUserSavedCourse(String userId) {
        validator.validOnCreatedUser(userId);
        try {
            return new ObjectMapper().readValue(repository.getByCoursesAllSavedDto(userId), new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @Override
    public void update(SavedCourseUpdateDto updateDto) {
    }

    @Override
    public void delete(String id) {
        validator.validateKey(id);
        repository.deleteByIdNoHard(id);
    }

    public void delete(String userId, String courseId) {
        validator.validOnCreatedUser(userId);
        repository.deleteThisUserSavedCourse(userId, courseId);
    }


}
