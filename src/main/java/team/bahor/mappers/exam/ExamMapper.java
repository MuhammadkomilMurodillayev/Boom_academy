package team.bahor.mappers.exam;

import org.mapstruct.*;
import org.springframework.stereotype.Component;
import team.bahor.dto.exam.exam.ExamCreateDtoBegin;
import team.bahor.dto.exam.exam.ExamDto;
import team.bahor.dto.exam.exam.ExamUpdateDto;
import team.bahor.entity.exam.Exam;
import team.bahor.mappers.base.AbstractMapper;

import java.lang.annotation.Target;
import java.util.List;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ExamMapper extends AbstractMapper<Exam, ExamDto, ExamCreateDtoBegin, ExamUpdateDto> {



    Exam fromUpdateDto(ExamUpdateDto updateDto, @MappingTarget Exam exam);

    @Override
    ExamDto toDto(Exam entity);

    @Override
    List<ExamDto> toDto(List<Exam> entities);

    @Override
    Exam fromCreateDto(ExamCreateDtoBegin createDto);

    @Override
    Exam fromUpdateDto(ExamUpdateDto updateDto);
}
