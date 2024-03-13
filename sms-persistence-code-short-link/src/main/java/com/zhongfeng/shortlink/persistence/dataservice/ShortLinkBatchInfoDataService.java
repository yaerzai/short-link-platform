package com.zhongfeng.shortlink.persistence.dataservice;

import com.zhongfeng.common.db.model.PageData;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkBatchInfoDataBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkBatchInfoQueryBO;

/**
 * @author yuzc
 * 2020/2/23 11:23
 * <p>
 * 短链信息管理
 */
public interface ShortLinkBatchInfoDataService {
    /***
     * 查询
     * @param  queryBO
     * @return 短链结果集
     */
    PageData<ShortLinkBatchInfoDataBO> query(ShortLinkBatchInfoQueryBO queryBO);

    /***
     * 添加
     * @param  dataBO
     */
    boolean add(ShortLinkBatchInfoDataBO dataBO);

    /**
     * 获取短链信息
     *
     * @param linkNo 链接编号
     * @return
     */
    ShortLinkBatchInfoDataBO get(String linkNo);

    /**
     * 删除短链信息
     *
     * @param linkNo 链接编号
     * @param linkBatchNo 短链批次
     * @return
     */
    boolean delete(String linkNo, String linkBatchNo);

    /**
     * 删除短链信息
     *
     * @param linkNo 链接编号
     * @return
     */
    boolean delete(String linkNo);

}
