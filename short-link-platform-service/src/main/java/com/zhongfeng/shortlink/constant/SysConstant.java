package com.zhongfeng.shortlink.constant;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 相关常量类
 *
 * @author luoli
 * create on 2019/4/17
 */
public class SysConstant {
    public static final ThreadPoolExecutor commonExecutors = new ThreadPoolExecutor(50, 50, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new DefaultThreadFactory("commonExecutors"));
    public static final ThreadPoolExecutor redisSyncExecutors = new ThreadPoolExecutor(10, 10, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new DefaultThreadFactory("redisSyncExecutors"));

    static {
        // 核心线程允许超时释放
        commonExecutors.allowCoreThreadTimeOut(true);
        redisSyncExecutors.allowCoreThreadTimeOut(true);
    }

}
