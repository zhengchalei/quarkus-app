package io.github.zhengchalei.common.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;
import javax.ws.rs.QueryParam;

@MappedSuperclass
public class BaseEntity extends PanacheEntityBase {

    @Id
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
