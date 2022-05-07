package team.bahor.services.lesson.answer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import team.bahor.dto.lesson.answer.AnswerLessonQuestionCreateDto;
import team.bahor.dto.lesson.answer.AnswerLessonQuestionDto;
import team.bahor.dto.lesson.answer.AnswerLessonQuestionUpdateDto;
import team.bahor.entity.lesson.AnswerLessonQuestion;
import team.bahor.mappers.lesson.AnswerLessonQuestionMapper;
import team.bahor.repositories.lesson.AnswerLessonQuestionRepository;
import team.bahor.services.base.AbstractService;
import team.bahor.validators.lesson.AnswerLessonQuestionValidator;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class AnswerLessonQuestionServiceImpl extends AbstractService<
        AnswerLessonQuestionRepository,
        AnswerLessonQuestionMapper,
        AnswerLessonQuestionValidator> implements AnswerLessonQuestionService {

    protected AnswerLessonQuestionServiceImpl(@Qualifier("answerLessonQuestionMapperImpl") AnswerLessonQuestionMapper mapper, AnswerLessonQuestionValidator validator, AnswerLessonQuestionRepository repository) {
        super(mapper, validator, repository);
    }

    @Override
    public String create(AnswerLessonQuestionCreateDto createDto) {
        validator.validOnCreate(createDto);
        repository.save(mapper.fromCreateDto(createDto));
        return "Saved";
    }

    @Override
    public void delete(String id) {
        validator.validateKey(id);
        repository.deletedById(id);
    }

    @Override
    public void update(AnswerLessonQuestionUpdateDto updateDto) {
        validator.validOnUpdate(updateDto);
        Optional<AnswerLessonQuestion> optionalAnswerLessonQuestion = repository.findByAnswerId(updateDto.getId());
        noEmpty(optionalAnswerLessonQuestion);
        AnswerLessonQuestion answerLessonQuestion = mapper.fromUpdateDto(updateDto, optionalAnswerLessonQuestion.get());
        repository.save(answerLessonQuestion);
    }

    public void updateLikeCount(String id){
        validator.validateKey(id);
        repository.updateLikeCount(id);
    }

    @Override
    public AnswerLessonQuestionDto get(String id) {
        validator.validateKey(id);
        Optional<AnswerLessonQuestion> optionalAnswerLessonQuestion = repository.findByAnswerId(id);

        noEmpty(optionalAnswerLessonQuestion);

        return mapper.toDto(optionalAnswerLessonQuestion.get());
    }

    private void noEmpty(Optional<AnswerLessonQuestion> optionalAnswerLessonQuestion) {
        if (optionalAnswerLessonQuestion.isEmpty())
            throw new RuntimeException("Not Found !");
    }

    @Override
    public List<AnswerLessonQuestionDto> getAll() {
        return null;
    }

    public List<AnswerLessonQuestionDto> getAll(String questionId) {
        validator.validQuestionId(questionId);
        List<AnswerLessonQuestion> answerLessonQuestionList = repository.findAllByQuestionId(questionId);
        return mapper.toDto(answerLessonQuestionList);
    }
}
