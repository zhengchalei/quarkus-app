package io.github.zhengchalei.module.dev.service;

import io.github.zhengchalei.module.dev.domain.GeneratorMetaData;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 **/
public interface GeneratorService {

    String buildPath(GeneratorMetaData metaData, String... paths);

    void createFileAndWrite(String path, String data, GeneratorMetaData metaData);

    void service(GeneratorMetaData metaData);

    void serviceImpl(GeneratorMetaData metaData);

    void resource(GeneratorMetaData metaData);

    @CheckedTemplate(requireTypeSafeExpressions = false)
    public static class Templates {
        public static native TemplateInstance resource(GeneratorMetaData data);

        public static native TemplateInstance service(GeneratorMetaData data);

        public static native TemplateInstance serviceImpl(GeneratorMetaData data);
    }
}
