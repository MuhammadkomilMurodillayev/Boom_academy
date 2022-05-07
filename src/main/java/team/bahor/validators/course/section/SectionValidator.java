package team.bahor.validators.course.section;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.bahor.dto.course.section.SectionCreateDto;
import team.bahor.dto.course.section.SectionUpdateDto;
import team.bahor.entity.courses.Course;
import team.bahor.entity.courses.Section;
import team.bahor.exeptions.ValidationException;
import team.bahor.exeptions.course.section.SectionForbiddenException;
import team.bahor.exeptions.course.section.SectionNotFoundException;
import team.bahor.repositories.auth.AuthUserRepository;
import team.bahor.repositories.course.CourseRepository;
import team.bahor.repositories.course.SectionRepository;
import team.bahor.utils.Utils;
import team.bahor.validators.base.AbstractValidator;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SectionValidator
        extends AbstractValidator<SectionCreateDto, SectionUpdateDto, String> {
    private final SectionRepository sectionRepository;
    private final CourseRepository courseRepository;
    private final AuthUserRepository authUserRepository;

    @Override
    public void validateKey(String id) throws ValidationException {
        Optional<Section> sectionOptional = sectionRepository.findByNoDeletedSection(id, Utils.getSessionId());
        if (sectionOptional.isEmpty())
            throw new SectionNotFoundException("Section not found");

    }

    @Override
    public void validOnCreate(SectionCreateDto dto) throws ValidationException{
        Optional<Course> byIdAndCreatedBy = courseRepository.findByIdAndCreatedBy(dto.getCourseId(), dto.getCreatedBy());
        if (byIdAndCreatedBy.isEmpty())
            throw new SectionForbiddenException("Not allowed");
    }


    public void validOnAuthorizated() throws ValidationException{
        if (Objects.isNull(authUserRepository.findByIdAuthorizated(Utils.getSessionId())))
            throw new SectionForbiddenException("Not allowed");
    }

    @Override
    public void validOnUpdate(SectionUpdateDto dto) throws ValidationException {
        Optional<Section> sectionOptional = sectionRepository.findByNoDeletedSection(dto.getId(), Utils.getSessionId());
        if (sectionOptional.isEmpty())
            throw new SectionNotFoundException("Section not found");
    }


    public short validOptionalSection(Optional<Section> optionalSection) throws ValidationException{
        if (optionalSection.isEmpty())
            throw new SectionNotFoundException("Section not found");
        return optionalSection.get().getPosition();
    }

}
