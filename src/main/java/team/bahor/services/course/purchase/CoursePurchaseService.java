package team.bahor.services.course.purchase;

import team.bahor.dto.course.purchase.CoursePurchaseCreateDto;
import team.bahor.dto.course.purchase.CoursePurchaseDto;
import team.bahor.dto.course.purchase.CoursePurchaseUpdateDto;
import team.bahor.services.base.GenericCrudService;

public interface CoursePurchaseService extends GenericCrudService<
        CoursePurchaseDto,
        CoursePurchaseCreateDto,
        CoursePurchaseUpdateDto,
        String> {
}
