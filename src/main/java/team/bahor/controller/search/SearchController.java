package team.bahor.controller.search;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.bahor.controller.AbstractController;
import team.bahor.dto.responce.DataDto;
import team.bahor.dto.search.ResultOfSearch;
import team.bahor.services.search.SearchService;

import java.util.List;

@RestController
public class SearchController extends AbstractController<SearchService> {
    public SearchController(SearchService service) {
        super(service);
    }

    @RequestMapping(value = PATH + "/search", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<ResultOfSearch>>> search(@RequestParam String search) {
        return new ResponseEntity<>(new DataDto<>(service.search(search)), HttpStatus.OK);
    }
}
