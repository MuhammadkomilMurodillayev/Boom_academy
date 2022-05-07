package team.bahor.services.search;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.bahor.dto.search.ResultOfSearch;
import team.bahor.exeptions.exam.BadCredentialsInformationExam;
import team.bahor.exeptions.search.BadCredentialsSearch;
import team.bahor.repositories.exam.ExamRepository;
import team.bahor.services.base.BaseGenericService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService implements BaseGenericService {
    private final ExamRepository repository;

    public List<ResultOfSearch> search(String search) {
        List<ResultOfSearch> result = null;
        try {
            result = new ObjectMapper().readValue(repository.search(search), new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new BadCredentialsSearch("search qilayotganda muammo bor");
        }
        return result;
    }
}
