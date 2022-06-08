package io.github.zhengchalei.common.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;
import javax.ws.rs.QueryParam;
import java.time.Instant;

@MappedSuperclass
public class BaseEntity extends PanacheEntity {

    @QueryParam("id")
    @Schema(description = "id", example = "1")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    /**
     * 乐观锁
     */
    @Version
    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
