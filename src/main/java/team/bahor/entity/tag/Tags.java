package team.bahor.entity.tag;

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
@Entity
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "tags_status_index", columnList = "status"),
        @Index(name = "tags_name_index", columnList = "name"),
        @Index(name = "tags_course_id_index", columnList = "courseId"),
        @Index(name = "tags_article_id_index", columnList = "articleId")
})
public class Tags extends Auditable {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String courseId;

    @Column(nullable = false)
    private String articleId;
}
