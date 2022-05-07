package team.bahor.validators.tag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.bahor.dto.tag.TagCreateDto;
import team.bahor.dto.tag.TagUpdateDto;
import team.bahor.entity.courses.Course;
import team.bahor.entity.tag.Tags;
import team.bahor.exeptions.ValidationException;
import team.bahor.exeptions.course.CourseNotFoundException;
import team.bahor.exeptions.tags.TagAlreadyExistsException;
import team.bahor.exeptions.tags.TagForbiddenException;
import team.bahor.exeptions.tags.TagsNotFoundException;
import team.bahor.repositories.course.CourseRepository;
import team.bahor.repositories.tag.TagsRepository;
import team.bahor.utils.Utils;
import team.bahor.validators.base.AbstractValidator;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TagsValidator extends AbstractValidator<
        TagCreateDto,
        TagUpdateDto,
        String> {

    private final TagsRepository tagsRepository;
    private final CourseRepository courseRepository;

    @Override
    public void validateKey(String id) throws ValidationException {
        Optional<Tags> tagsOptional = tagsRepository.noDeletedTag(id);
        if (tagsOptional.isEmpty())
            throw new TagsNotFoundException("Tag Not Found !");
    }

    public void validateCourseId(String id) throws ValidationException {
        Optional<Course> courseOptional = courseRepository.activeThisCourse(id);
        if (courseOptional.isEmpty())
            throw new CourseNotFoundException("Course Not Found !");
    }

    @Override
    public void validOnCreate(TagCreateDto tagCreateDto) throws ValidationException {
        if (Objects.nonNull(tagsRepository.equalsElements(tagCreateDto.getCourseId(), tagCreateDto.getName())))
            throw new TagAlreadyExistsException("Tag Already Exists !");
        if (courseRepository.createdThisCourse(tagCreateDto.getCourseId()).equals(Utils.getSessionId()))
            throw new TagForbiddenException("Not Allowed !");
    }

    @Override
    public void validOnUpdate(TagUpdateDto updateDto) throws ValidationException {

    }

    public Tags validOnUpdateAndReturn(TagUpdateDto updateDto) throws ValidationException {
        Optional<Tags> tagsOptional = tagsRepository.noDeletedTag(updateDto.getId());
        if (tagsOptional.isEmpty())
            throw new TagsNotFoundException("Tag Not Found !");
        if (courseRepository.createdThisCourse(updateDto.getCourseId()).equals(Utils.getSessionId()))
            throw new TagForbiddenException("Not Allowed !");
        return tagsOptional.get();
    }
}
