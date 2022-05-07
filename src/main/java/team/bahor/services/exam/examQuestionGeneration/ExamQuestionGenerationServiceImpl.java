package team.bahor.services.exam.examQuestionGeneration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import team.bahor.dto.exam.examQuestionGeneration.ExamQuestionGenerationCreateDto;
import team.bahor.dto.exam.examQuestionGeneration.ExamQuestionGenerationDto;
import team.bahor.dto.exam.examQuestionGeneration.ExamQuestionGenerationUpdateDto;
import team.bahor.entity.exam.ExamQuestionGeneration;
import team.bahor.mappers.exam.ExamQuestionGenerationMapper;
import team.bahor.repositories.exam.ExamQuestionGenerationRepository;
import team.bahor.services.base.AbstractService;
import team.bahor.services.exam.examQuestionGeneration.ExamQuestionGenerationService;
import team.bahor.validators.exam.ExamQuestionGenerationValidator;

import java.util.List;
import java.util.UUID;

@Service
public class ExamQuestionGenerationServiceImpl extends AbstractService<
        ExamQuestionGenerationRepository,
        ExamQuestionGenerationMapper,
        ExamQuestionGenerationValidator
        > implements ExamQuestionGenerationService {
    public ExamQuestionGenerationServiceImpl(@Qualifier("examQuestionGenerationMapperImpl") ExamQuestionGenerationMapper mapper,
                                             ExamQuestionGenerationValidator validator,
                                             ExamQuestionGenerationRepository repository) {
        super(mapper, validator, repository);
    }

    @Override
    public String create(ExamQuestionGenerationCreateDto createDto) {
        ExamQuestionGeneration examQuestionGeneration = mapper.fromCreateDto(createDto);
        examQuestionGeneration.setId(UUID.randomUUID().toString());
        return repository.save(examQuestionGeneration).getId();
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(ExamQuestionGenerationUpdateDto updateDto) {
//        repository.update(updateDto);
    }

    @Override
    public ExamQuestionGenerationDto get(String id) {
        return null;
    }

    @Override
    public List<ExamQuestionGenerationDto> getAll() {
        return null;
    }

    public List<ExamQuestionGenerationDto> getAllByExamId(String id) {
        return mapper.toDto(repository.findAllByDeletedFalseAndExamId(id));
    }

    public void update(String id, String k, Integer v) {
        repository.update(id,k,v);
    }
}
