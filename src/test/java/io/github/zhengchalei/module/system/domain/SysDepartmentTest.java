package io.github.zhengchalei.module.system.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.zhengchalei.common.Util;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.util.List;

@QuarkusTest
@Transactional
class SysDepartmentTest {

    private final Logger log = LoggerFactory.getLogger(SysDepartmentTest.class);

    @Test
    public void test() throws JsonProcessingException {
        SysDepartment root = SysDepartment.findRoots().get(0);

        SysDepartment l1 = new SysDepartment();
        l1.name = "北京分公司";
        l1.parentId = root.getId();
        l1.persistAndFlush();

        SysDepartment l2 = new SysDepartment();
        l2.name = "北京分公司开发部";
        l2.parentId = l1.getId();
        l2.persistAndFlush();

        List<SysDepartment> findAll = SysDepartment.findAll().list();
        log.info("list: {}", new ObjectMapper().writeValueAsString(Util.tree(findAll)));
    }
}
