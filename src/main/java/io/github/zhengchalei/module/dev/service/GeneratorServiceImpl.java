package io.github.zhengchalei.module.dev.service;

import io.github.zhengchalei.module.dev.domain.GeneratorMetaData;
import org.apache.commons.lang3.StringUtils;
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
    public String buildPath(GeneratorMetaData metaData, String... paths) {
        String projectPath = System.getProperty("user.dir");
        String packagePath = StringUtils.join(projectPath,
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
        return returnPath.replaceAll("\\.", Matcher.quoteReplacement(File.separator));
    }

    @Override
    public void createFileAndWrite(String path, String data, GeneratorMetaData metaData) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                boolean bool = file.createNewFile();
                logger.info("创建文件: {}, {}", file.getName(), bool);
            } catch (IOException e) {
                throw new RuntimeException(e);
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
    public void service(GeneratorMetaData metaData) {
        String filePath = buildPath(metaData, "service", metaData.entityName) + "Service.java";
        String render = Templates.service(metaData).render();
        createFileAndWrite(filePath, render, metaData);
    }

    @Override
    public void serviceImpl(GeneratorMetaData metaData) {
        String filePath = buildPath(metaData, "service", "impl", metaData.entityName) + "ServiceImpl.java";
        String render = Templates.serviceImpl(metaData).render();
        createFileAndWrite(filePath, render, metaData);
    }

    @Override
    public void resource(GeneratorMetaData metaData) {
        String filePath = buildPath(metaData, "resource", metaData.entityName) + "Resource.java";
        String render = Templates.resource(metaData).render();
        createFileAndWrite(filePath, render, metaData);
    }
}