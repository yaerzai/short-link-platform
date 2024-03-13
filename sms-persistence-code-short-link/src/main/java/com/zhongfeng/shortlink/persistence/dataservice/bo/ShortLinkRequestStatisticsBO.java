package com.zhongfeng.shortlink.persistence.dataservice.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

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
public class ShortLinkRequestStatisticsBO implements Serializable {
    private static final long serialVersionUID = 3204210810714463245L;


    /**
     * 链接编号
     */
    private String linkNo;

    /**
     * 请求次数
     */
    private Integer totalNum;

    public ShortLinkBatchInfoDataBO buildDataBO() {
        ShortLinkBatchInfoDataBO shortLinkBatchInfoDataBO = new ShortLinkBatchInfoDataBO();
        shortLinkBatchInfoDataBO.setLinkNo(linkNo);
        shortLinkBatchInfoDataBO.setClickNum(totalNum);
        return shortLinkBatchInfoDataBO;
    }

}