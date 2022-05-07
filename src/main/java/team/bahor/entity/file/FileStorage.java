package team.bahor.entity.file;

import lombok.Getter;
import lombok.Setter;
import team.bahor.entity.base.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
public class FileStorage extends Auditable {

    @Column(nullable = false)
    String lessonId;

    @Column(nullable = false)
    String path;

    @Column(nullable = false)
    String originalName;

    @Column(nullable = false)
    String generatedName;

    String createdBy;

    String type;
}
