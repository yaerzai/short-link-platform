package com.zhongfeng.shortlink.task;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.Page;
import com.zhongfeng.common.base.service.BaseService;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkCustomerConfigDataBO;
import com.zhongfeng.shortlink.persistence.mapper.ShortLinkCustomerConfigMapper;
import com.zhongfeng.shortlink.persistence.model.ShortLinkCustomerConfig;
import com.zhongfeng.shortlink.persistence.model.ShortLinkCustomerConfigExample;
import com.zhongfeng.shortlink.service.ShortLinkRedisCacheService;
import com.zhongfeng.sms.persistence.enums.RecordStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 失效链接清除任务
 *
 * @auther fanjun
 */
@Service
@Slf4j
public class ShortLinkInvalidClearTask {

    @Resource
    private ShortLinkCustomerConfigMapper shortLinkCustomerConfigMapper;
    @Autowired
    private BaseService baseService;
    @Resource
    private ShortLinkRedisCacheService shortLinkRedisCacheService;
    /**
     * 失效清除，每小时一次
     */
    @Scheduled(fixedDelay = 3600_000)
    @Transactional
    public void invalidClear() {
        if (baseService.getNodeId() != 1) {
            return;
        }
        ShortLinkCustomerConfigExample configExample = new ShortLinkCustomerConfigExample();
        configExample.createCriteria()
                .andExpirationTimeLessThan(DateUtil.date())
                .andStatusEqualTo(RecordStatusEnum.OK.getCodeId());
        int batchSize = 10000;
        RowBounds rowBounds = new RowBounds(0, batchSize);
        Page<ShortLinkCustomerConfig> configPage = shortLinkCustomerConfigMapper.selectByExampleWithRowbounds(configExample, rowBounds);
        List<ShortLinkCustomerConfig> configList = configPage.getResult();
        if (CollUtil.isEmpty(configList)) {
            return;
        }
        List<Long> idList = configList.stream().map(ShortLinkCustomerConfig::getId).collect(Collectors.toList());
        //配置状态改为无效
        configExample = new ShortLinkCustomerConfigExample();
        configExample.createCriteria().andIdIn(idList);
        ShortLinkCustomerConfig configUpdate = new ShortLinkCustomerConfig();
        configUpdate.setStatus((byte) 2); //失效过期
        shortLinkCustomerConfigMapper.updateByExampleSelective(configUpdate, configExample);

        List<ShortLinkCustomerConfigDataBO> dataList = configList.stream().map(ShortLinkCustomerConfigDataBO::buildByPO).collect(Collectors.toList());
        shortLinkRedisCacheService.remove(dataList);
//        //查询批量文件并删除
//        List<String> fileNameList = configList.stream().filter(item -> LinkTypeEnum.BATCH.eq(item.getLinkType())).map(ShortLinkCustomerConfig::getShortLinkAddress).collect(Collectors.toList());
//        if (CollUtil.isNotEmpty(fileNameList)) {
//            for (String fileName : fileNameList) {
//                String completeRemoteDir = UploadPathEnum.getCompleteRemoteDir(dataService.getSysParam(SysParamEnum.UPLOAD_FILE_DIR), UploadPathEnum.WebShortLinkMobileFile, OperatorContext.getOperator());
//                if (completeRemoteDir.contains("\\")) {
//                    completeRemoteDir = completeRemoteDir.replace("\\", "/");
//                }
//                sftpBaseService.delete(completeRemoteDir, fileName);
//            }
//        }
        //存在下一页的情况下
        if (configList.size() == batchSize && configPage.getPages() > 1) {
            invalidClear();
        }
    }

}
