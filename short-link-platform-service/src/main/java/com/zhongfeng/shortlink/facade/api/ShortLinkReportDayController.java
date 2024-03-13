package com.zhongfeng.shortlink.facade.api;

import cn.hutool.extra.cglib.CglibUtil;
import com.zhongfeng.common.db.model.PageData;
import com.zhongfeng.common.swagger.model.ResultData;
import com.zhongfeng.shortlink.api.dto.ShortLinkReportDayCreateDTO;
import com.zhongfeng.shortlink.api.dto.ShortLinkReportDayListDTO;
import com.zhongfeng.shortlink.api.dto.ShortLinkReportDayQueryDTO;
import com.zhongfeng.shortlink.api.service.ShortLinkReportDayService;
import com.zhongfeng.shortlink.persistence.dataservice.ShortLinkReportDayCreateService;
import com.zhongfeng.shortlink.persistence.dataservice.ShortLinkReportDayDataService;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkReportDayDataBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkReportDayQueryBO;
import com.zhongfeng.shortlink.utils.PageDataUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author codescript.build
 */
@RestController
@Slf4j
public class ShortLinkReportDayController implements ShortLinkReportDayService {

    @Resource
    private ShortLinkReportDayDataService shortLinkReportDayDataService;
    @Resource
    private ShortLinkReportDayCreateService shortLinkReportDayCreateService;

    @Override
    public ResultData<PageData<ShortLinkReportDayListDTO>> query(ShortLinkReportDayQueryDTO queryDTO) {
        ShortLinkReportDayQueryBO queryBO = CglibUtil.copy(queryDTO, ShortLinkReportDayQueryBO.class);
        PageData<ShortLinkReportDayDataBO> dataList = shortLinkReportDayDataService.query(queryBO);
        return new ResultData<>(PageDataUtils.changePageData(dataList, item-> CglibUtil.copy(item, ShortLinkReportDayListDTO.class)));
    }

    @Override
    public ResultData creatReportDay(@Valid ShortLinkReportDayCreateDTO createDTO) {
        shortLinkReportDayCreateService.createDayReport(createDTO.getReportDate(), createDTO.getOperatorId());
        return ResultData.SUCCESS;
    }
}
