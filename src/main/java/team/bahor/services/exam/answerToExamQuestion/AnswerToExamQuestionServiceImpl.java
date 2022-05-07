package team.bahor.services.exam.answerToExamQuestion;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import team.bahor.dto.exam.answerToExamQuestion.AnswerToExamQuestionCreateDto;
import team.bahor.dto.exam.answerToExamQuestion.AnswerToExamQuestionDto;
import team.bahor.dto.exam.answerToExamQuestion.AnswerToExamQuestionUpdateDto;
import team.bahor.entity.exam.AnswerToExamQuestion;

import team.bahor.exeptions.exam.NotFoundAnswerToExamQuestion;
import team.bahor.mappers.exam.AnswerToExamQuestionMapper;
import team.bahor.repositories.exam.AnswerToExamQuestionRepository;
import team.bahor.services.base.AbstractService;
import team.bahor.utils.UtilsForSessionUser;
import team.bahor.validators.exam.AnswerToExamQuestionValidator;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class AnswerToExamQuestionServiceImpl extends AbstractService<
        AnswerToExamQuestionRepository,
        AnswerToExamQuestionMapper,
        AnswerToExamQuestionValidator
        > implements AnswerToExamQuestionService {

    private final UtilsForSessionUser utils;

    public AnswerToExamQuestionServiceImpl(@Qualifier("answerToExamQuestionMapperImpl") AnswerToExamQuestionMapper mapper, AnswerToExamQuestionValidator validator, AnswerToExamQuestionRepository repository, UtilsForSessionUser utils) {
        super(mapper, validator, repository);
        this.utils = utils;
    }

    @Override
    public String create(AnswerToExamQuestionCreateDto createDto) {
        AnswerToExamQuestion answerToExamQuestion = mapper.fromCreateDto(createDto);
        answerToExamQuestion.setId(UUID.randomUUID().toString());
        return repository.save(answerToExamQuestion).getId();
    }

    @Override
    public void delete(String id) {
        validator.deleted(id);
        repository.deleted(id);
    }

    @Override
    public void update(AnswerToExamQuestionUpdateDto updateDto) {
        AnswerToExamQuestion answerToExamQuestion = repository.findById(updateDto.getId()).orElseThrow(() -> {
            throw new NotFoundAnswerToExamQuestion("not found answer");
        });
        AnswerToExamQuestion answerToExamQuestion1 = mapper.fromUpdateDto(updateDto, answerToExamQuestion);
        repository.save(answerToExamQuestion1);
    }

    @Override
    public AnswerToExamQuestionDto get(String id) {
        validator.validateKey(id);
        AnswerToExamQuestion byIdAndDeletedIsFalse = repository.getByIdAndDeletedIsFalse(id);
        if (Objects.isNull(byIdAndDeletedIsFalse)) {
            throw new NotFoundAnswerToExamQuestion("not found answer");
        }
        return mapper.toDto(byIdAndDeletedIsFalse);
    }

    @Override
    public List<AnswerToExamQuestionDto> getAll() {
        return mapper.toDto(repository.getByDeletedFalse());
    }

    public List<AnswerToExamQuestionDto> getAll(String id) {
        repository.getByExamQuestionIdAndDeletedFalse(id).forEach(answerToExamQuestion -> {
            answerToExamQuestion.setCorrect(false);
        });
        return mapper.toDto(repository.getByExamQuestionIdAndDeletedFalse(id));
    }


    public boolean isTeacher(String id) {
        return repository.isTeacher(id, utils.getSessionId());
    }
}
