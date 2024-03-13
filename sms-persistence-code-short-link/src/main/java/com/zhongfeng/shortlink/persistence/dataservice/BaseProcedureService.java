package com.zhongfeng.shortlink.persistence.dataservice;

import cn.hutool.core.lang.func.Func1;
import cn.hutool.core.lang.func.VoidFunc1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 类描述
 *
 * @auther fanjun
 */
@Slf4j
public abstract class BaseProcedureService {
    @Value("${spring.datasource.ds.driver-class-name:mysql}")
    private String driverClass;
    @Value("${spring.datasource.ds.jdbc-url:mysql}")
    private String dbUrl;
    @Value("${spring.datasource.ds.username:mysql}")
    private String dbUsername;
    @Value("${spring.datasource.ds.password:mysql}")
    private String dbPassword;

    private AtomicBoolean isLoad = new AtomicBoolean();

    public <T> T execFunc(String funcExp, VoidFunc1<CallableStatement> set, Func1<CallableStatement, T> ret) {
        Connection conn = null;
        CallableStatement clbStmt = null; // CallableStatement对象
        try {
            conn = getConnection();
            clbStmt = conn.prepareCall(funcExp);
            set.call(clbStmt);
            clbStmt.executeUpdate();
            return ret.call(clbStmt);
        } catch (SQLException e) {
            log.error("执行存储过程异常", e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            log.error("执行存储过程异常", e);
            throw new RuntimeException(e);
        } finally {
            try {
                clbStmt.close();
            } catch (SQLException e) {
                log.error("clbStmt.close error", e);
            }
            try {
                conn.close();
            } catch (SQLException e) {
                log.error("conn.close error", e);
            }
        }
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            if (!isLoad.getAndSet(true)) {
                // 加载数据库驱动类
                Class.forName(driverClass);
            }
            // 获取数据库连接对象
            conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (Throwable e) {
            log.error("获取连接异常", e);
        }
        return conn;
    }

}
