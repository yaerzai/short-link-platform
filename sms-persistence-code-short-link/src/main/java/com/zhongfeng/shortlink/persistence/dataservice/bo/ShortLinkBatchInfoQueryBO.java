package com.zhongfeng.shortlink.persistence.dataservice.bo;

import com.zhongfeng.common.db.model.BaseQueryBO;
import com.zhongfeng.shortlink.persistence.model.ShortLinkBatchInfoExample;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
public class ShortLinkBatchInfoQueryBO extends BaseQueryBO {
    private static final long serialVersionUID = 5245655845124597775L;
    /**
     * 链接编号
     */
    private String linkNo;

    /**
     * 短链批次,用于单个连接多次生成的情况
     */
    private String linkBatchNo;

    public ShortLinkBatchInfoExample buildExample() {
        ShortLinkBatchInfoExample example = new ShortLinkBatchInfoExample();
        example.setOrderByClause(getOrderBy());
        ShortLinkBatchInfoExample.Criteria criteria = example.createCriteria();
        criteria.andLinkNoEqualTo(linkNo).andLinkBatchNoEqualTo(linkBatchNo);
        return example;
    }
}