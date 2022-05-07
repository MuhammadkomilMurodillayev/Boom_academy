package team.bahor.entity.articles;

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
@Table(name = "saved_articles", indexes = {
        @Index(name = "saved_article_article_id_index", columnList = "articleId"),
        @Index(name = "saved_article_user_id_index", columnList = "userId"),
        @Index(name = "saved_article_status_index", columnList = "status")
})
@Entity
@NoArgsConstructor
public class SavedArticle extends Auditable {
    @Column(nullable = false)
    private String articleId;

    @Column(nullable = false)
    private String userId;

}
