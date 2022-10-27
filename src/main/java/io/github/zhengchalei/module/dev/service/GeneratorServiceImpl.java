package io.github.zhengchalei.module.dev.service;

import io.github.zhengchalei.common.exception.ServiceException;
import io.github.zhengchalei.module.dev.domain.GenMetaData;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;

/**
 * @author <a href="mailto:stone981023@gmail.com">zhengchalei</a>
 **/
@Singleton
public class GeneratorServiceImpl implements GeneratorService {

    private final Logger logger = LoggerFactory.getLogger(GeneratorService.class);

    @Override
    public String buildPath(GenMetaData metaData, String... paths) {
        String projectPath = System.getProperty("user.dir");
        String packagePath = String.join("",
            projectPath,
            File.separator,
            "src",
            File.separator,
            "main",
            File.separator,
            "java",
            File.separator,
            metaData.modulePath,
            File.separator
        );
        String returnPath = packagePath + String.join(File.separator, paths);
        return returnPath.replace("\\.", Matcher.quoteReplacement(File.separator));
    }

    @Override
    public void createFileAndWrite(String path, String data, GenMetaData metaData) {
        File file = new File(path);
        if (!file.exists() || metaData.rewrite) {
            try {
                boolean bool = file.createNewFile();
                logger.info("创建文件: {}, {}", file.getName(), bool);
            } catch (IOException e) {
                throw new ServiceException(e);
            }
            try (FileOutputStream outputStream = new FileOutputStream(path)) {
                outputStream.write(data.getBytes(StandardCharsets.UTF_8));
                logger.info("写入文件: {}", file.getName());
            } catch (IOException e) {
                logger.error("写入文件失败: {}, msg: {}", file.getName(), e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void service(GenMetaData metaData) {
        String filePath = buildPath(metaData, "service", metaData.entityName) + "Service.java";
        String render = Templates.service(metaData).render();
        createFileAndWrite(filePath, render, metaData);
    }

    @Override
    public void serviceImpl(GenMetaData metaData) {
        String filePath = buildPath(metaData, "service", "impl", metaData.entityName) + "ServiceImpl.java";
        String render = Templates.serviceImpl(metaData).render();
        createFileAndWrite(filePath, render, metaData);
    }

    @Override
    public void resource(GenMetaData metaData) {
        String filePath = buildPath(metaData, "resource", metaData.entityName) + "Resource.java";
        String render = Templates.resource(metaData).render();
        createFileAndWrite(filePath, render, metaData);
    }

    @CheckedTemplate(requireTypeSafeExpressions = false)
    public static class Templates {

        public static native TemplateInstance resource(GenMetaData data);

        public static native TemplateInstance service(GenMetaData data);

        public static native TemplateInstance serviceImpl(GenMetaData data);
    }
}
