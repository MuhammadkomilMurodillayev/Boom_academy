package team.bahor.entity.quiz;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.bahor.entity.base.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "quizzes", indexes = {
        @Index(name = "quiz_status_index", columnList = "status"),
        @Index(name = "quiz_section_id_index", columnList = "sectionId")
})
@Entity
@NoArgsConstructor
public class Quiz extends Auditable {
    @Column(nullable = false)
    private String sectionId;

    @Column(nullable = false)
    private Long duration; //milliseconds

    @Column(nullable = false)
    private Short questionCount;

    @Column(nullable = false)
    private Short maxMark;

    @Column(nullable = false)
    private String title;

    private String description;
}



