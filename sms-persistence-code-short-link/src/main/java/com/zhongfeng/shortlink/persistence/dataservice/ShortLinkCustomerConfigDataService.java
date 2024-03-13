package com.zhongfeng.shortlink.persistence.dataservice;

import com.zhongfeng.common.db.model.PageData;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkCustomerConfigDataBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkCustomerConfigQueryBO;
import com.zhongfeng.shortlink.persistence.model.ShortLinkCustomerConfig;

import java.util.List;

/**
 * @author yuzc
 * 2020/2/23 11:23
 * <p>
 * 客户链接配置信息管理
 */
public interface ShortLinkCustomerConfigDataService {
    /***
     * 查询
     * @param  queryBO
     * @return 结果集
     */
    PageData<ShortLinkCustomerConfigDataBO> query(ShortLinkCustomerConfigQueryBO queryBO);

    /***
     * 添加
     * @param  dataBO
     */
    boolean add(ShortLinkCustomerConfigDataBO dataBO);

    /***
     * 修改
     * @param  dataBO
     */
    boolean upd(ShortLinkCustomerConfigDataBO dataBO);

    /**
     * 获取客户链接配置信息
     *
     * @param linkNo 链接编号
     * @return
     */
    ShortLinkCustomerConfigDataBO get(String linkNo);

    /**
     * 获取客户链接配置信息
     * @param address
     * @return
     */
    ShortLinkCustomerConfigDataBO getByShortAddress(String address);

    /**
     * 删除
     *
     * @param linkNo 链接编号
     * @return
     */
    boolean delete(String linkNo);


    /**
     * 同一天时间，相同客户，相同原始长链，是否存在
     */
    ShortLinkCustomerConfig isExistSameLinkNo(ShortLinkCustomerConfigDataBO dateBO);
}
