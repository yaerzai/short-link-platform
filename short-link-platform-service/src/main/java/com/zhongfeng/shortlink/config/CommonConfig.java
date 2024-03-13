package com.zhongfeng.shortlink.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.shardingsphere.jdbc.spring.boot.sharding.SpringBootShardingRuleConfigurationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import javax.servlet.MultipartConfigElement;
import java.io.File;

/**
 * @author gan.feng
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({SpringBootShardingRuleConfigurationProperties.class, DataSourceProperties.class})
public class CommonConfig {
    @Bean
    public PathMatchingResourcePatternResolver resourcePatternResolver() {
        return new PathMatchingResourcePatternResolver();
    }

    /**
     * 解决文件上传,临时文件夹被程序自动删除问题
     * <p>
     * 文件上传时自定义临时路径
     *
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        String location = System.getProperty("user.dir") + File.separator + "apps" + File.separator + "uploadtmp";
        log.info("上传临时目录路径：{}", location);
        File tmpFile = new File(location);
        if (!tmpFile.exists() && !tmpFile.mkdirs()) {
            log.error("上传临时目录路径 创建失败！ {}", location);
        }
        //2.该处就是指定的路径(需要提前创建好目录，否则上传时会抛出异常)
        factory.setLocation(location);
        return factory.createMultipartConfig();
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        // 通过该方法对mapper对象进行设置，所有序列化的对象都将按改规则进行系列化
        // Include.Include.ALWAYS 默认
        // Include.NON_DEFAULT 属性为默认值不序列化
        // Include.NON_EMPTY 属性为 空（""） 或者为 NULL 都不序列化，则返回的json是没有这个字段的。这样对移动端会更省流量
        // Include.NON_NULL 属性为NULL 不序列化,就是为null的字段不参加序列化
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }
}
