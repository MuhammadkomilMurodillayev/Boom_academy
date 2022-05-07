package team.bahor.services.lesson.notes;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import team.bahor.dto.lesson.note.NotesToLessonsCreateDto;
import team.bahor.dto.lesson.note.NotesToLessonsDto;
import team.bahor.dto.lesson.note.NotesToLessonsUpdateDto;
import team.bahor.entity.lesson.NotesToLessons;
import team.bahor.mappers.lesson.NotesToLessonsMapper;
import team.bahor.repositories.lesson.NotesToLessonsRepository;
import team.bahor.services.base.AbstractService;
import team.bahor.validators.lesson.NotesToLessonsValidator;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class NotesToLessonsServiceImpl extends AbstractService<
        NotesToLessonsRepository,
        NotesToLessonsMapper,
        NotesToLessonsValidator> implements NotesToLessonsService {

    protected NotesToLessonsServiceImpl(@Qualifier("notesToLessonsMapperImpl") NotesToLessonsMapper mapper, NotesToLessonsValidator validator, NotesToLessonsRepository repository) {
        super(mapper, validator, repository);
    }

    @Override
    public String create(NotesToLessonsCreateDto createDto) {
        validator.validOnCreate(createDto);
        repository.save(mapper.fromCreateDto(createDto));
        return "Saved";
    }

    @Override
    public void delete(String id) {
        validator.validateKey(id);
        repository.deleteFindById(id);
    }

    @Override
    public void update(NotesToLessonsUpdateDto updateDto) {
        validator.validOnUpdate(updateDto);

        Optional<NotesToLessons> optionalNotesToLessons = repository.findByNotesToLessonsId(updateDto.getId());
        noEmpty(optionalNotesToLessons);

        repository.save(mapper.fromUpdateDto(updateDto, optionalNotesToLessons.get()));

    }

    @Override
    public NotesToLessonsDto get(String id) {
        validator.validateKey(id);

        Optional<NotesToLessons> optionalNotesToLessons = repository.findByNotesToLessonsId(id);
        noEmpty(optionalNotesToLessons);

        return mapper.toDto(optionalNotesToLessons.get());
    }

    private void noEmpty(Optional<NotesToLessons> optionalNotesToLessons) {
        if (optionalNotesToLessons.isEmpty())
            throw new RuntimeException("Not Found");
    }

    @Override
    public List<NotesToLessonsDto> getAll() {
        return null;
    }

    public List<NotesToLessonsDto> getAll(String userId) {
        validator.validAuthUserAccount(userId);
        List<NotesToLessons> userAllNotes = repository.findByAuthUserAllNotes(userId);
        return mapper.toDto(userAllNotes);
    }
}
