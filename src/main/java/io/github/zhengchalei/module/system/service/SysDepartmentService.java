package io.github.zhengchalei.module.system.service;

import io.github.zhengchalei.common.jpa.QueryBuilder;
import io.github.zhengchalei.module.system.domain.SysDepartment;
import io.quarkus.panache.common.Page;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 **/
@Singleton
@Transactional
public class SysDepartmentService {

    private final Logger logger = LoggerFactory.getLogger(SysDepartmentService.class);

    @Inject
    EntityManager entityManager;

    private QueryBuilder<SysDepartment> queryBuilder(SysDepartment sysDepartment) {
        logger.info("queryBuilder args: {}", sysDepartment);
        QueryBuilder<SysDepartment> queryBuilder = new QueryBuilder<>(entityManager, SysDepartment.class);
        if (sysDepartment.getId() != null) {
            Predicate predicate = queryBuilder.cb.equal(
                    queryBuilder.root.get("id"),
                    sysDepartment.getId()
            );
            queryBuilder.where(predicate);
        }
        if (sysDepartment.name != null) {
            Predicate predicate = queryBuilder.cb.equal(
                    queryBuilder.root.get("name"),
                    sysDepartment.name
            );
            queryBuilder.where(predicate);
        }
        if (sysDepartment.description != null) {
            Predicate predicate = queryBuilder.cb.equal(
                    queryBuilder.root.get("description"),
                    sysDepartment.description
            );
            queryBuilder.where(predicate);
        }
        if (sysDepartment.sort != null) {
            Predicate predicate = queryBuilder.cb.equal(
                    queryBuilder.root.get("sort"),
                    sysDepartment.sort
            );
            queryBuilder.where(predicate);
        }
        if (sysDepartment.parentId != null) {
            Predicate predicate = queryBuilder.cb.equal(
                    queryBuilder.root.get("parentId"),
                    sysDepartment.parentId
            );
            queryBuilder.where(predicate);
        }
        return queryBuilder;
    }


    public List<SysDepartment> findPage(Page page, SysDepartment sysDepartment) {
        return SysDepartment.findAll().page(page).list();
    }


    public long findCount(Page page, SysDepartment sysDepartment) {
        return SysDepartment.findAll().page(page).count();
    }


    public List<SysDepartment> findAll(SysDepartment sysDepartment) {
        QueryBuilder<SysDepartment> queryBuilder = this.queryBuilder(sysDepartment);
        return queryBuilder.exec().getResultList();
    }


//    public List<SysDepartment> tree() {
//        List<SysDepartment> listAll = SysDepartment.listAll();
//        return $.tree(listAll);
//    }


    public SysDepartment findById(Long id) {
        SysDepartment data = SysDepartment.findById(id);
        if (data == null) {
            throw new NotFoundException();
        }
        return data;
    }


    public void save(SysDepartment sysDepartment) {
        sysDepartment.persistAndFlush();
    }


    public void update(Long id, SysDepartment sysDepartment) {
        SysDepartment flush = findById(id);
        // change
        flush.persistAndFlush();
    }


    public boolean delete(Long id) {
        return SysDepartment.deleteById(id);
    }
}
