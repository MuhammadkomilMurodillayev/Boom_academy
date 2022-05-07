package team.bahor.services.tag;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import team.bahor.dto.course.CourseDto;
import team.bahor.dto.tag.TagCreateDto;
import team.bahor.dto.tag.TagDto;
import team.bahor.dto.tag.TagUpdateDto;
import team.bahor.entity.courses.Course;
import team.bahor.entity.tag.Tags;
import team.bahor.exeptions.tags.TagsNotFoundException;
import team.bahor.mappers.course.CourseMapper;
import team.bahor.mappers.tag.TagsMapper;
import team.bahor.repositories.tag.TagsRepository;
import team.bahor.services.base.AbstractService;
import team.bahor.validators.tag.TagsValidator;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TagsServiceImpl extends AbstractService<
        TagsRepository,
        TagsMapper,
        TagsValidator> implements TagsService {

    private final CourseMapper courseMapper;

    protected TagsServiceImpl(@Qualifier("tagsMapperImpl") TagsMapper mapper, TagsValidator validator, TagsRepository repository, CourseMapper courseMapper) {
        super(mapper, validator, repository);
        this.courseMapper = courseMapper;
    }

    @Override
    public String create(TagCreateDto createDto) {
        validator.validOnCreate(createDto);
        Tags tag = mapper.fromCreateDto(createDto);
        tag.setArticleId("-");
        tag.setId(UUID.randomUUID().toString());
        repository.save(tag);
        return "Created";
    }

    @Override
    public void delete(String id) {
        validator.validateKey(id);
        repository.deletedThisIdTag(id);
    }

    @Override
    public void update(TagUpdateDto updateDto) {
        Tags tags = validator.validOnUpdateAndReturn(updateDto);
        repository.save(mapper.fromUpdateDto(updateDto, tags));
    }

    @Override
    public TagDto get(String id) {
        validator.validateKey(id);
        Optional<Tags> tagsOptional = repository.noDeletedTag(id);
        if (tagsOptional.isPresent())
            return mapper.toDto(tagsOptional.get());
        throw new TagsNotFoundException("Tag Not Found !");
    }

    @Override
    public List<TagDto> getAll() {
        return null;
    }

    public List<TagDto> getAll(String courseId) {
        validator.validateCourseId(courseId);
        List<Tags> tagList = repository.findAllCourseTags(courseId);
        return mapper.toDto(tagList);
    }

    public List<CourseDto> searchCourse(String name) {
        List<Course> allTagsCourses = repository.findAllTagsCourses(name);
        return courseMapper.toDto(allTagsCourses);
    }

}
