package com.zhongfeng.shortlink.persistence.dataservice.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageRowBounds;
import com.zhongfeng.common.db.model.PageData;
import com.zhongfeng.shortlink.persistence.dataservice.ShortLinkInfoDataService;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkInfoDataBO;
import com.zhongfeng.shortlink.persistence.dataservice.bo.ShortLinkInfoQueryBO;
import com.zhongfeng.shortlink.persistence.mapper.ShortLinkInfoMapper;
import com.zhongfeng.shortlink.persistence.model.ShortLinkInfo;
import com.zhongfeng.shortlink.persistence.model.ShortLinkInfoExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author yuzc
 * @date 2020/2/23 12:20
 */
@Service
@Slf4j
public class ShortLinkInfoDataServiceImpl implements ShortLinkInfoDataService {
    @Resource
    private ShortLinkInfoMapper mapper;

    @Override
    public PageData<ShortLinkInfoDataBO> query(ShortLinkInfoQueryBO queryBO) {
        ShortLinkInfoExample example = queryBO.buildExample();
        Page<ShortLinkInfo> shortLinkInfoPage = mapper.selectByExampleWithRowbounds(example, new PageRowBounds(queryBO.getPageNo(), queryBO.getPageSize()));
        PageData<ShortLinkInfoDataBO> pageData = PageData.getPageData(shortLinkInfoPage);
        if (!CollectionUtils.isEmpty(shortLinkInfoPage)) {
            pageData.setData(shortLinkInfoPage.stream().map(ShortLinkInfoDataBO::buildByPO).collect(Collectors.toList()));
        }
        return pageData;
    }

    @Override
    public boolean add(ShortLinkInfoDataBO dataBO) {
        return mapper.insertSelective(dataBO.buildPO()) == 1;
    }

//    @Cached(cacheName = "ShortLinkInfoDataBO", key = "shortLinkNo", cachedNull = false)
    @Override
    public ShortLinkInfoDataBO get(String shortLinkNo, String tableTime) {
        ShortLinkInfoExample example = new ShortLinkInfoExample();
        example.createCriteria().andShortLinkNoEqualTo(shortLinkNo).andTableTimeEqualTo(tableTime);
        List<ShortLinkInfo> list = mapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return ShortLinkInfoDataBO.buildByPO(list.get(0));
    }

//    @CacheRemove(cacheName = "ShortLinkInfoDataBO", key = "shortLinkNo")
//    @Override
//    public boolean delete(@Param("shortLinkNo") String shortLinkNo) {
//        if (StringUtils.isBlank(shortLinkNo)) {
//            log.error("删除条件不能为空 {}", shortLinkNo);
//            return false;
//        }
//        ShortLinkInfoExample example = new ShortLinkInfoExample();
//        example.createCriteria().andShortLinkNoEqualTo(shortLinkNo);
//        return mapper.deleteByExample(example) == 1;
//    }

//    @CacheRemove(cacheName = "ShortLinkInfoDataBO", key = "*")
    @Override
    public boolean deleteByLinkNo(String linkNo, String tableTime) {
        if (StringUtils.isBlank(linkNo)) {
            log.error("删除条件不能为空 {}", linkNo);
            return false;
        }
        ShortLinkInfoExample example = new ShortLinkInfoExample();
        example.createCriteria().andTableTimeEqualTo(tableTime).andLinkNoEqualTo(linkNo);
        return mapper.deleteByExample(example) >= 1;
    }

    @Override
    public int each(String linkNo, String tableTime, int pageSize, Consumer<ShortLinkInfoDataBO> callback) {
        AtomicInteger count = new AtomicInteger();
        Long lastId = 0L;
        while (true) {
            ShortLinkInfoQueryBO infoQueryBO = new ShortLinkInfoQueryBO();
            infoQueryBO.setOrderBy("id");
            infoQueryBO.setLinkNo(linkNo);
            infoQueryBO.setPageNo(1);
            infoQueryBO.setPageSize(pageSize);
            infoQueryBO.setLastId(lastId);
            infoQueryBO.setTableTime(tableTime);
            long startTime = System.currentTimeMillis();
            PageData<ShortLinkInfoDataBO> infoPage = query(infoQueryBO);
            List<ShortLinkInfoDataBO> data = infoPage.getData();
            if (CollUtil.isEmpty(data)) {
                break;
            }
            log.info("[InfoQuery] ShortLinkInfo分页查询, linkNo: {}, lastId:{}, 查询条数:{}, 耗时: {}", linkNo, lastId, data.size(), showTimeNow(startTime));
            for (ShortLinkInfoDataBO dataBO : data) {
                callback.accept(dataBO);
                count.incrementAndGet();
            }
            if (infoPage.getPages() <= 1) {
                break;
            }
            lastId = data.get(data.size() - 1).getId();
        }
        return count.get();
    }

    /**
     * 耗时显示
     * @param startTime
     * @return
     */
    public static String showTimeNow(long startTime) {
        long endTime = System.currentTimeMillis();
        return showTime(endTime - startTime);
    }

    /**
     * 耗时显示
     * @param spaceTime
     * @return
     */
    public static String showTime(long spaceTime) {
        StringBuilder builder = StrUtil.builder();
        long hour = spaceTime / 3600_000;
        long minute = (spaceTime / 60_000) % 60;
        long second = (spaceTime / 1000) % 60;
        long ms = spaceTime % 1000;
        if (hour > 0) {
            builder.append(hour).append("时");
        }
        if (minute > 0 || (minute == 0 && hour > 0)) {
            if (hour > 0 && minute < 10) {
                builder.append("0");
            }
            builder.append(minute).append("分");
        }
        builder.append(second);
        if (spaceTime/1000 == 0) {
            builder.append(".").append(String.format("%03d",ms));
        }
        builder.append("秒");
        return builder.toString();
    }
}
