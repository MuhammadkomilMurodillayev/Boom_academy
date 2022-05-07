package team.bahor.services.lesson.question;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import team.bahor.dto.lesson.answer.AnswerLessonQuestionDto;
import team.bahor.dto.lesson.question.LessonQuestionCreateDto;
import team.bahor.dto.lesson.question.LessonQuestionDto;
import team.bahor.dto.lesson.question.LessonQuestionUpdateDto;
import team.bahor.entity.lesson.AnswerLessonQuestion;
import team.bahor.entity.lesson.LessonQuestion;
import team.bahor.mappers.lesson.AnswerLessonQuestionMapper;
import team.bahor.mappers.lesson.LessonQuestionMapper;
import team.bahor.repositories.lesson.AnswerLessonQuestionRepository;
import team.bahor.repositories.lesson.LessonQuestionRepository;
import team.bahor.services.base.AbstractService;
import team.bahor.utils.Utils;
import team.bahor.validators.lesson.LessonQuestionValidator;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class LessonQuestionServiceImpl extends AbstractService<
        LessonQuestionRepository,
        LessonQuestionMapper,
        LessonQuestionValidator> implements LessonQuestionService {

    private final AnswerLessonQuestionRepository answerLessonQuestionRepository;
    private final AnswerLessonQuestionMapper answerLessonQuestionMapper;

    protected LessonQuestionServiceImpl(@Qualifier("lessonQuestionMapperImpl") LessonQuestionMapper mapper, LessonQuestionValidator validator, LessonQuestionRepository repository, AnswerLessonQuestionRepository answerLessonQuestionRepository, AnswerLessonQuestionMapper answerLessonQuestionMapper) {
        super(mapper, validator, repository);
        this.answerLessonQuestionRepository = answerLessonQuestionRepository;
        this.answerLessonQuestionMapper = answerLessonQuestionMapper;
    }

    @Override
    public String create(LessonQuestionCreateDto createDto) {
        validator.validOnCreate(createDto);
        repository.save(mapper.fromCreateDto(createDto));
        return "Saved";
    }

    @Override
    public void delete(String id) {
        validator.validateKey(id);
        repository.deletedByThisId(id);
    }

    @Override
    public void update(LessonQuestionUpdateDto updateDto) {
        validator.validOnUpdate(updateDto);
        Optional<LessonQuestion> questionId = repository.findByQuestionId(updateDto.getId(), Utils.getSessionId());

        noEmpty(questionId);

        LessonQuestion lessonQuestion = mapper.fromUpdateDto(updateDto, questionId.get());
        repository.save(lessonQuestion);
    }

    public void updateLikeCount(String id){
        validator.validateKey(id);
        repository.updateLikeCount(id);
    }

    @Override
    public LessonQuestionDto get(String id) {
        validator.validateKey(id);
        Optional<LessonQuestion> questionId = repository.findByQuestionId(id, Utils.getSessionId());

        noEmpty(questionId);

        LessonQuestionDto lessonQuestionDto = mapper.toDto(questionId.get());
        List<AnswerLessonQuestion> answerQuestionList = answerLessonQuestionRepository.findAllByQuestionId(lessonQuestionDto.getId());
        List<AnswerLessonQuestionDto> answerLessonQuestionDtoList = answerLessonQuestionMapper.toDto(answerQuestionList);

        lessonQuestionDto.setAnswerLessonQuestionDtoList(answerLessonQuestionDtoList);
        return lessonQuestionDto;
    }

    private void noEmpty(Optional<LessonQuestion> questionId) {
        if (questionId.isEmpty())
            throw new RuntimeException("LessonQuestion Not Found !");
    }

    @Override
    public List<LessonQuestionDto> getAll() {
        return null;
    }

    public List<LessonQuestionDto> getAll(String lessonId) {
        validator.validateLessonKey(lessonId);

        List<LessonQuestionDto> lessonQuestionDtoList = mapper.toDto(repository.findByLessonIdAllQuestions(lessonId));

        lessonQuestionDtoList.forEach(questionDto -> {
            List<AnswerLessonQuestion> answerQuestionList = answerLessonQuestionRepository.findAllByQuestionId(questionDto.getId());
            questionDto.setAnswerLessonQuestionDtoList(answerLessonQuestionMapper.toDto(answerQuestionList));
        });
        return lessonQuestionDtoList;
    }
}
