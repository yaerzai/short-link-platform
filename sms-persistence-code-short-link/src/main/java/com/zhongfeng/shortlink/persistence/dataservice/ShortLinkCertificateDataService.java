package com.zhongfeng.shortlink.persistence.dataservice;

import com.zhongfeng.common.db.model.PageData;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkCertificateDataBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkCertificateQueryBO;
import java.util.List;

/**
 * @author codescript.build
 */
public interface ShortLinkCertificateDataService {

    /**
     * 查询长链凭证
     *
     * @param dataBO 查询条件
     */
    PageData<ShortLinkCertificateDataBO> query(ShortLinkCertificateQueryBO dataBO);

    /**
     * 查询长链凭证详情
     *
     * @param id 主键
     */
    ShortLinkCertificateDataBO get(Long id);

    /**
     * 添加长链凭证
     *
     * @param dataBO 新增数据
     */
    boolean add(ShortLinkCertificateDataBO dataBO);

    /**
     * 更改长链凭证
     *
     * @param dataBO 修改数据
     * @return 修改结果
     */
    boolean update(ShortLinkCertificateDataBO dataBO);

    /**
     * 删除长链凭证
     *
     * @param id 主键
     * @return 删除结果
     */
    boolean delete(Long id);

    /**
     * 批量删除长链凭证
     *
     * @param idList 主键列表
     * @return 删除数量
     */
    int batchDelete(List<Long> idList);

    /**
     * 检查长链接地址
     * @param longLinkList
     * @return
     */
    String checkLongLink(List<String> longLinkList);

}
