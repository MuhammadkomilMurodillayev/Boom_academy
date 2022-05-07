package team.bahor.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum CourseCategory {
    BUSINESS,
    DEVELOPMENT,
    FINANCE_AND_ACCOUNTING,
    IT_AND_SOFTWARE,
    OFFICE_SOFTWARE,
    PERSONAL_GROWTH,
    DESIGN,
    MARKETING,
    HEALTH,
    EDUCATIONAL_AND_ACADEMIC_DISCIPLINES;

    public static List<CourseCategory> getAll() {

        return Arrays.stream(CourseCategory.values()).toList();

    }

}
