package io.github.zhengchalei.common.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.Hibernate;

import java.util.Objects;

/**
 * 基础实体
 *
 * @author 郑查磊
 */
@MappedSuperclass
public abstract class BaseEntity extends PanacheEntityBase {

    @Id
    @QueryParam("id")
    @Schema(description = "id", example = "1")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    /**
     * 乐观锁
     */
    @Version
    public Long version;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BaseEntity that = (BaseEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
