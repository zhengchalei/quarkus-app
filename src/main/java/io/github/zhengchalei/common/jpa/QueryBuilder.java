package io.github.zhengchalei.common.jpa;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * QueryBuilder
 *
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 **/
public class QueryBuilder<T> {

    public final EntityManager em;

    public final CriteriaBuilder cb;

    public final CriteriaQuery<T> query;

    public final Root<T> root;

    public final List<Predicate> predicates;

    /**
     * 查询构建器
     *
     * @param entityManager 实体管理器
     * @param resultClass   结果类
     */
    public QueryBuilder(EntityManager entityManager, Class<T> resultClass) {
        this.em = entityManager;
        this.cb = this.em.getCriteriaBuilder();
        this.query = this.cb.createQuery(resultClass);
        this.root = this.query.from(resultClass);
        // resultClass.getFields().length
        this.predicates = new ArrayList<>();
    }

    /**
     * 执行
     *
     * @return {@code TypedQuery<T>}
     */
    public TypedQuery<T> exec() {
        return this.em.createQuery(query);
    }

    /**
     * 添加Where条件
     *
     * @param predicate 谓词
     * @return {@code QueryBuilder<T>}
     */
    public QueryBuilder<T> where(Predicate predicate) {
        this.query.where(predicate);
        return this;
    }

    public QueryBuilder<T> where(Predicate... predicate) {
        this.query.where(predicate);
        return this;
    }

    public QueryBuilder<T> join(String field, JoinType joinType) {
        this.root.fetch(field, joinType);
        return this;
    }
}
