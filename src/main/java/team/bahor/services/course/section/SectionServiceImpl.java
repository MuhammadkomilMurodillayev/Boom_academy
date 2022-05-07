package team.bahor.services.course.section;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import team.bahor.dto.course.section.SectionCreateDto;
import team.bahor.dto.course.section.SectionDto;
import team.bahor.dto.course.section.SectionPositionUpdateDto;
import team.bahor.dto.course.section.SectionUpdateDto;
import team.bahor.entity.courses.Section;
import team.bahor.mappers.course.SectionMapper;
import team.bahor.repositories.course.SectionRepository;
import team.bahor.services.base.AbstractService;
import team.bahor.utils.Utils;
import team.bahor.validators.course.section.SectionValidator;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
public class SectionServiceImpl extends AbstractService<
        SectionRepository,
        SectionMapper,
        SectionValidator> implements SectionService {

    protected SectionServiceImpl(@Qualifier("sectionMapperImpl") SectionMapper mapper, SectionValidator validator, SectionRepository repository) {
        super(mapper, validator, repository);
    }

    @Override
    public String create(SectionCreateDto createDto) {

        validator.validOnCreate(createDto);
        Section section = mapper.fromCreateDto(createDto);

        short length = repository.countSectionByCourseId(section.getCourseId());
        if (length > section.getPosition() && section.getPosition() > 0)
            repository.updatePositionSection(section.getCourseId(), section.getPosition(), Utils.getSessionId());
        else
            section.setPosition((short) (length + 1));
        section.setId(UUID.randomUUID().toString());
        repository.save(section);
        return "Saved section !";
    }

    @Override
    public SectionDto get(String id) {
        Optional<Section> optionalSection = repository.findByNoDeletedSection(id, Utils.getSessionId());
        validator.validOptionalSection(optionalSection);
        return mapper.toDto(optionalSection.get());
    }


    @Override
    public List<SectionDto> getAll() {
//        List<Section> sectionList = repository.findAllSections();
//        return mapper.toDto(sectionList);
        return null;
    }

    public List<SectionDto> getCourseSections(String id) {
        validator.validOnAuthorizated();
        List<Section> allCourseSections = repository.findAllByCourseIdAAndDelete(id, Utils.getSessionId());
        return mapper.toDto(allCourseSections);
    }


    @Override //Todo nimagadir mapper nori ishlavoti
    public void update(SectionUpdateDto updateDto) {
        Optional<Section> optionalSection = repository.findByNoDeletedSection(updateDto.getId(), Utils.getSessionId());
        validator.validOptionalSection(optionalSection);
        Section section = mapper.fromUpdateDto(updateDto, optionalSection.get());
        repository.save(section);
    }


    @Override
    public void delete(String id) {
        validator.validateKey(id);
        repository.deleteBySection(id, Utils.getSessionId());
    }

    public void updatePosition(SectionPositionUpdateDto dto) {
        Optional<Section> optionalSection = repository.findByNoDeletedSection(dto.getId(), Utils.getSessionId());
        short def = validator.validOptionalSection(optionalSection);

        Section section = mapper.fromUpdateDto(dto, optionalSection.get());

        short length = repository.countSectionByCourseId(section.getCourseId());
        short position = section.getPosition();
        // l = 7, np = 6, d = 2;
        if (length > position && position < def) {
            repository.updatePositionRightSection(section.getCourseId(), position, def, Utils.getSessionId());
            repository.updatePositionSection(section.getId(), Utils.getSessionId(), position);
        } else if (length > position && position > def) {
            repository.updatePositionLeftSection(section.getCourseId(), position, def, Utils.getSessionId());
            repository.updatePositionSection(section.getCourseId(), Utils.getSessionId(), position);
        } else
            repository.updatePositionSection(section.getId(), Utils.getSessionId(), (short) (length + 1));
    }


}
