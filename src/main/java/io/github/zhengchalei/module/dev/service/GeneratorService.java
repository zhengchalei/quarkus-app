package io.github.zhengchalei.module.dev.service;

import io.github.zhengchalei.module.dev.domain.GenMetaData;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 **/
public interface GeneratorService {

    String buildPath(GenMetaData metaData, String... paths);

    void createFileAndWrite(String path, String data, GenMetaData metaData);

    void service(GenMetaData metaData);

    void serviceImpl(GenMetaData metaData);

    void resource(GenMetaData metaData);

}
