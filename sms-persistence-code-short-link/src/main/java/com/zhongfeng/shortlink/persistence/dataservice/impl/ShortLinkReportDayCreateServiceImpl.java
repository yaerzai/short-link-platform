package com.zhongfeng.shortlink.persistence.dataservice.impl;

import com.zhongfeng.shortlink.persistence.dataservice.BaseProcedureService;
import com.zhongfeng.shortlink.persistence.dataservice.ShortLinkReportDayCreateService;
import org.springframework.stereotype.Service;

import java.sql.Types;

/**
 * 类描述
 *
 * @auther fanjun
 */
@Service
public class ShortLinkReportDayCreateServiceImpl extends BaseProcedureService implements ShortLinkReportDayCreateService {
    // 生成日报表
    @Override
    public int createDayReport(String reportDate, String operatorId) {
        return execFunc("{call shortlink_report_day(?, ?, ?)}", clbStmt-> {
            clbStmt.setString(1, reportDate);
            clbStmt.setString(2, operatorId);
            clbStmt.registerOutParameter(3, Types.INTEGER);
        }, clbStmt-> clbStmt.getInt(3));

    }
}
