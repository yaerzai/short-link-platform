package com.zhongfeng.shortlink.persistence.dataservice;

import com.zhongfeng.common.db.model.PageData;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkReportDayDataBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkReportDayQueryBO;

/**
 * @author codescript.build
 */
public interface ShortLinkReportDayDataService {

    /**
     * 查询短链日报表
     * 
     * @param dataBO 查询条件
     */
    PageData<ShortLinkReportDayDataBO> query(ShortLinkReportDayQueryBO dataBO);

}
