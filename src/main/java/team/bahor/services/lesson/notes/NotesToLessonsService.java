package team.bahor.services.lesson.notes;

import team.bahor.dto.lesson.note.NotesToLessonsCreateDto;
import team.bahor.dto.lesson.note.NotesToLessonsDto;
import team.bahor.dto.lesson.note.NotesToLessonsUpdateDto;
import team.bahor.services.base.GenericCrudService;

public interface NotesToLessonsService extends GenericCrudService<
        NotesToLessonsDto,
        NotesToLessonsCreateDto,
        NotesToLessonsUpdateDto,
        String> {
}
