package team.bahor.services.exam.exam;

import team.bahor.dto.exam.exam.ExamCreateDtoBegin;
import team.bahor.dto.exam.exam.ExamDto;
import team.bahor.dto.exam.exam.ExamUpdateDto;
import team.bahor.services.base.GenericCrudService;

public interface ExamService extends GenericCrudService<
        ExamDto,
        ExamCreateDtoBegin,
        ExamUpdateDto,
        String
        > {
}
