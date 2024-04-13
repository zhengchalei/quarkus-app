package io.github.zhengchalei.common.model;

import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

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


    public long skip() {
        if (this.index <= 0) {
            return 0;
        }
        return (this.index - 1) * this.size;
    }

    public long limit() {
        if (this.index <= 0) {
            return 10;
        }
        return this.size;
    }

}
