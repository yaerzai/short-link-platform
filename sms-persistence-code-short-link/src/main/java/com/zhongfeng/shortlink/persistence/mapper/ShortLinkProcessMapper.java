package com.zhongfeng.shortlink.persistence.mapper;

import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkBatchInfoDataBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkInfoDataBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkRequestStatisticsBO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author mbg
 */
public interface ShortLinkProcessMapper {
    /**
     * 批量新增
     *
     * @param list
     * @return
     */
    int batchInsertShortLinkInfo(@Param("tableTime") String tableTime, @Param("list") List<ShortLinkInfoDataBO> list);

    /**
     * 批量修改
     *
     * @param list
     * @return
     */
    int batchUpdateShortLinkBatchInfo(@Param("list") List<ShortLinkBatchInfoDataBO> list);

    /**
     * 统计访问批次
     * @param beginTime 开始时间
     * @param endTime 接收时间
     * @param groupBy   分组字段
     * @return
     */
    List<ShortLinkRequestStatisticsBO> queryRequestInfo(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime, @Param("groupBy") String groupBy);
}