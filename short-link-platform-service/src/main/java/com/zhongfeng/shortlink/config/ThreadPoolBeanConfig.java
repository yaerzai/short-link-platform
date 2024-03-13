package com.zhongfeng.shortlink.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 *
 * @auther fanjun
 */
@Configuration
@EnableAsync
@Slf4j
public class ThreadPoolBeanConfig {

    @Bean
    public TaskExecutor commonExecutors() {
        int corePoolSize = 10;
        int maxPoolSize = 10;
        int queueCapacity = 100;
        int keepAliveSeconds = 100;
        String namePrefix = "async-service-";

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(corePoolSize);
        // 设置最大线程数
        executor.setMaxPoolSize(maxPoolSize);
        // 设置队列容量
        executor.setQueueCapacity(queueCapacity);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(keepAliveSeconds);
        // 设置默认线程名称
        executor.setThreadNamePrefix(namePrefix);
        // 设置默认线程名称
        executor.setThreadNamePrefix("async-service-");
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        log.info("创建一个线程池 corePoolSize is [" + corePoolSize + "] maxPoolSize is [" + maxPoolSize + "] queueCapacity is [" + queueCapacity +
                "] keepAliveSeconds is [" + keepAliveSeconds + "] namePrefix is [" + namePrefix + "].");
        return executor;
    }
}
