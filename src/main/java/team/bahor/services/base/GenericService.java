package team.bahor.services.base;

import team.bahor.dto.BaseGenericDto;

import java.io.Serializable;
import java.util.List;

public interface GenericService <
        D extends BaseGenericDto,
        K extends Serializable
        > extends BaseGenericService{

    D get(K id);

    List<D> getAll();
}