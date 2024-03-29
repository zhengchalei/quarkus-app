package io.github.zhengchalei.common.model;

import com.querydsl.jpa.impl.JPAQuery;
import io.github.zhengchalei.module.system.domain.SysUser;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * 分页
 *
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 **/
public class Page {

    /**
     * The current page index (0-based).
     */
    @Description("分页的起始页")
    @Schema(defaultValue = "1")
    @QueryParam("index")
    public long index = 1;

    /**
     * The current page size;
     */
    @Description("查询数量")
    @Schema(defaultValue = "20")
    @QueryParam("size")
    public long size = 20;

    public Page() {
    }

    /**
     * Builds a page of the given size.
     *
     * @param size the page size
     * @throws IllegalArgumentException if the page size is less than or equal to 0
     * @see #ofSize(long)
     */
    public Page(long size) {
        this(1, size);
    }

    /**
     * Builds a page of the given index and size.
     *
     * @param index the page index (0-based)
     * @param size  the page size
     * @throws IllegalArgumentException if the page index is less than 0
     * @throws IllegalArgumentException if the page size is less than or equal to 0
     * @see #of(long, long)
     */
    public Page(long index, long size) {
        if (index < 0) throw new IllegalArgumentException("Page index must be >= 0 : " + index);
        if (size <= 0) throw new IllegalArgumentException("Page size must be > 0 : " + size);
        this.index = index;
        this.size = size;
    }

    /**
     * Builds a page of the given index and size.
     *
     * @param index the page index (0-based)
     * @param size  the page size
     * @throws IllegalArgumentException if the page index is less than 0
     * @throws IllegalArgumentException if the page size is less than or equal to 0
     */
    public static Page of(long index, long size) {
        return new Page(index, size);
    }

    /**
     * Builds a page of the given size.
     *
     * @param size the page size
     * @throws IllegalArgumentException if the page size is less than or equal to 0
     */
    public static Page ofSize(long size) {
        return new Page(size);
    }

    /**
     * Returns a new page with the next page index and the same size.
     *
     * @return a new page with the next page index and the same size.
     * @see #previous()
     */
    public Page next() {
        return new Page(index + 1, size);
    }

    /**
     * Returns a new page with the previous page index and the same size, or this page if it is the first page.
     *
     * @return a new page with the next page index and the same size, or this page if it is the first page.
     * @see #next()
     */
    public Page previous() {
        return index > 0 ? new Page(index - 1, size) : this;
    }

    /**
     * Returns a new page with the first page index (0) and the same size, or this page if it is the first page.
     *
     * @return a new page with the first page index (0) and the same size, or this page if it is the first page.
     */
    public Page first() {
        return index > 0 ? new Page(0, size) : this;
    }

    /**
     * Returns a new page at the given page index and the same size, or this page if the page index is the same.
     *
     * @param newIndex the new page index
     * @return a new page at the given page index and the same size, or this page if the page index is the same.
     */
    public Page index(long newIndex) {
        return newIndex != index ? new Page(newIndex, size) : this;
    }

    public long skip() {
        return (this.index - 1) * this.size;
    }

    public long limit() {
        return this.size;
    }

    public <T> List<T> fetch(JPAQuery<T> query) {
        return query.offset(skip()).limit(limit()).fetch();
    }
}
