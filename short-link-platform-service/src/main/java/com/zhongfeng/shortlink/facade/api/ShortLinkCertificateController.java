package com.zhongfeng.shortlink.facade.api;

import cn.hutool.extra.cglib.CglibUtil;
import com.zhongfeng.common.db.model.PageData;
import com.zhongfeng.common.swagger.model.ResultData;
import com.zhongfeng.shortlink.api.dto.*;
import com.zhongfeng.shortlink.api.service.ShortLinkCertificateService;
import com.zhongfeng.shortlink.persistence.dataservice.ShortLinkCertificateDataService;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkCertificateDataBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkCertificateQueryBO;
import com.zhongfeng.shortlink.utils.PageDataUtils;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author codescript.build
 */
@RestController
@Slf4j
public class ShortLinkCertificateController implements ShortLinkCertificateService {

    @Resource
    private ShortLinkCertificateDataService shortLinkCertificateDataService;

    @Override
    public ResultData<PageData<ShortLinkCertificateListDTO>> query(ShortLinkCertificateQueryDTO queryDTO) {
        ShortLinkCertificateQueryBO queryBO = CglibUtil.copy(queryDTO, ShortLinkCertificateQueryBO.class);
        PageData<ShortLinkCertificateDataBO> dataList = shortLinkCertificateDataService.query(queryBO);
        return new ResultData<>(PageDataUtils.changePageData(dataList, item-> CglibUtil.copy(item, ShortLinkCertificateListDTO.class)));
    }

    @Override
    public ResultData<ShortLinkCertificateDetailDTO> detail(Long id) {
        ShortLinkCertificateDataBO dataBO = shortLinkCertificateDataService.get(id);
        ShortLinkCertificateDetailDTO dto = null;
        if (dataBO != null) {
            dto = CglibUtil.copy(dataBO, ShortLinkCertificateDetailDTO.class);
        }
        return new ResultData<>(dto);
    }

    @Override
    public ResultData add(ShortLinkCertificateAddDTO addDTO) {
        ShortLinkCertificateDataBO dataBO = CglibUtil.copy(addDTO, ShortLinkCertificateDataBO.class);
        shortLinkCertificateDataService.add(dataBO);
        return ResultData.SUCCESS;
    }

    @Override
    public ResultData modify(ShortLinkCertificateModifyDTO modifyDTO) {
        ShortLinkCertificateDataBO dataBO = CglibUtil.copy(modifyDTO, ShortLinkCertificateDataBO.class);
        shortLinkCertificateDataService.update(dataBO);
        return ResultData.SUCCESS;
    }

    @Override
    public ResultData remove(Long id) {
        shortLinkCertificateDataService.delete(id);
        return ResultData.SUCCESS;
    }

    @Override
    public ResultData removes(List<Long> idList) {
        shortLinkCertificateDataService.batchDelete(idList);
        return ResultData.SUCCESS;
    }

}
