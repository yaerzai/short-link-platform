package com.zhongfeng.shortlink.persistence.dataservice.bo;

import com.zhongfeng.shortlink.persistence.model.ShortLinkCertificate;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author codescript.build
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShortLinkCertificateDataBO implements Serializable {
    private static final long serialVersionUID = 0L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 长链接唯一识别码
     */
    private String uniqueLongLink;

    /**
     * 客户编号
     */
    private String customerNo;

    /**
     * 客户账号
     */
    private String userNo;

    /**
     * 长链接地址
     */
    private String address;

    /**
     * 凭证文件地址
     */
    private String certificates;

    /**
     * 类型  0：精确匹配 1：域名匹配
     */
    private Byte type;

    /**
     * 状态 0：正常  1：关闭
     */
    private Byte status;

    /**
     * 链接用途
     */
    private String purpose;

    /**
     * 最后操作员
     */
    private String operatorId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public static ShortLinkCertificateDataBO buildByPO(ShortLinkCertificate po){
        ShortLinkCertificateDataBO bo = new ShortLinkCertificateDataBO();
        bo.setId(po.getId());
        bo.setUniqueLongLink(po.getUniqueLongLink());
        bo.setCustomerNo(po.getCustomerNo());
        bo.setUserNo(po.getUserNo());
        bo.setAddress(po.getAddress());
        bo.setCertificates(po.getCertificates());
        bo.setType(po.getType());
        bo.setStatus(po.getStatus());
        bo.setPurpose(po.getPurpose());
        bo.setOperatorId(po.getOperatorId());
        bo.setCreateTime(po.getCreateTime());
        bo.setUpdateTime(po.getUpdateTime());
        return bo;
    }

    public ShortLinkCertificate buildPO(){
        ShortLinkCertificate po = new ShortLinkCertificate();
        po.setId(id);
        po.setUniqueLongLink(uniqueLongLink);
        po.setCustomerNo(customerNo);
        po.setUserNo(userNo);
        po.setAddress(address);
        po.setCertificates(certificates);
        po.setType(type);
        po.setStatus(status);
        po.setPurpose(purpose);
        po.setOperatorId(operatorId);
        po.setCreateTime(createTime);
        po.setUpdateTime(updateTime);
        return po;
    }
}

