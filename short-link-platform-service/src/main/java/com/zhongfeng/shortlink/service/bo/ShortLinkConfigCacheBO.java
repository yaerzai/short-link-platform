package com.zhongfeng.shortlink.service.bo;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkCustomerConfigDataBO;
import lombok.Data;

/**
 * 类描述
 *
 * @auther fanjun
 */
@Data
public class ShortLinkConfigCacheBO {

    private ShortLinkCustomerConfigDataBO shortLinkConfig;

    private DateTime cacheTime;

    public ShortLinkConfigCacheBO(ShortLinkCustomerConfigDataBO shortLinkConfig) {
        this.shortLinkConfig = shortLinkConfig;
        cacheTime = DateUtil.date();
    }
}
