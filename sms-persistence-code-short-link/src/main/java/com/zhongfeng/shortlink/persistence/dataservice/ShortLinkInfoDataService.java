package com.zhongfeng.shortlink.persistence.dataservice;

import com.zhongfeng.common.db.model.PageData;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkInfoDataBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkInfoQueryBO;

import java.util.function.Consumer;

/**
 * @author yuzc
 * 2020/2/23 11:23
 * <p>
 * 短链信息管理
 */
public interface ShortLinkInfoDataService {
    /***
     * 查询
     * @param  queryBO
     * @return 短链结果集
     */
    PageData<ShortLinkInfoDataBO> query(ShortLinkInfoQueryBO queryBO);

    /***
     * 添加
     * @param  dataBO
     */
    boolean add(ShortLinkInfoDataBO dataBO);

    /**
     * 获取短链信息
     *
     * @param shortLinkNo 短链编号
     * @return
     */
    ShortLinkInfoDataBO get(String shortLinkNo, String tableTime);

//    /**
//     * 删除短链信息
//     *
//     * @param shortLinkNo 短链编号
//     * @return
//     */
//    boolean delete(String shortLinkNo);

    /**
     * 根据链接编号删除
     *
     * @param linkNo 链接编号
     * @return
     */
    boolean deleteByLinkNo(String linkNo, String tableTime);

    /**
     * 遍历
     * @param linkNo
     * @param tableTime
     * @param callback
     */
    int each(String linkNo, String tableTime, int pageSize, Consumer<ShortLinkInfoDataBO> callback);
}
