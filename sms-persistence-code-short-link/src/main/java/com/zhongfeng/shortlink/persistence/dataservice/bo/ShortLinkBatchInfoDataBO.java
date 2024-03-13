package com.zhongfeng.shortlink.persistence.dataservice.bo;

import com.zhongfeng.shortlink.persistence.model.ShortLinkBatchInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mbg
 */
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShortLinkBatchInfoDataBO implements Serializable {
    private static final long serialVersionUID = -663579778983171006L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 链接编号
     */
    private String linkNo;

    /**
     * 短链批次,日期+4位序列号
     */
    private String linkBatchNo;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 手机号个数
     */
    private Integer mobileNum;

    /**
     * 点击次数
     */
    private Integer clickNum;

    /**
     * 过期时间
     */
    private Date expireTime;

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

    public static ShortLinkBatchInfoDataBO buildByPO(ShortLinkBatchInfo po) {
        ShortLinkBatchInfoDataBO shortLinkBatchInfoDataBO = new ShortLinkBatchInfoDataBO();
        shortLinkBatchInfoDataBO.setId(po.getId());
        shortLinkBatchInfoDataBO.setLinkNo(po.getLinkNo());
        shortLinkBatchInfoDataBO.setLinkBatchNo(po.getLinkBatchNo());
        shortLinkBatchInfoDataBO.setFileName(po.getFileName());
        shortLinkBatchInfoDataBO.setMobileNum(po.getMobileNum());
        shortLinkBatchInfoDataBO.setClickNum(po.getClickNum());
        shortLinkBatchInfoDataBO.setExpireTime(po.getExpireTime());
        shortLinkBatchInfoDataBO.setOperatorId(po.getOperatorId());
        shortLinkBatchInfoDataBO.setCreateTime(po.getCreateTime());
        shortLinkBatchInfoDataBO.setUpdateTime(po.getUpdateTime());
        return shortLinkBatchInfoDataBO;
    }

    public ShortLinkBatchInfo buildPO() {
        ShortLinkBatchInfo shortLinkBatchInfo = new ShortLinkBatchInfo();
        shortLinkBatchInfo.setLinkNo(linkNo);
        shortLinkBatchInfo.setLinkBatchNo(linkBatchNo);
        shortLinkBatchInfo.setFileName(fileName);
        shortLinkBatchInfo.setMobileNum(mobileNum);
        shortLinkBatchInfo.setExpireTime(expireTime);
        shortLinkBatchInfo.setOperatorId(operatorId);
        return shortLinkBatchInfo;
    }

}