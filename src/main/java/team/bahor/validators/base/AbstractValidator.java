package team.bahor.validators.base;

import team.bahor.dto.BaseGenericDto;
import team.bahor.dto.GenericDto;
import team.bahor.entity.courses.Course;
import team.bahor.exeptions.ValidationException;
import team.bahor.exeptions.course.CategoryNotAvailableException;
import team.bahor.utils.Utils;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractValidator<CD extends BaseGenericDto, UD extends GenericDto, K extends Serializable> implements BaseGenericValidator {

    public abstract void validateKey(K id) throws ValidationException;

    public abstract void validOnCreate(CD cd) throws ValidationException;

    public abstract void validOnUpdate(UD cd) throws ValidationException;

    public void validPermission(String... roles){

    }

}

