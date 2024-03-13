package com.zhongfeng.shortlink.persistence.dataservice.bo;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.zhongfeng.shortlink.persistence.model.ShortLinkInfo;
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
public class ShortLinkInfoDataBO implements Serializable {
    private static final long serialVersionUID = 3204210810714463245L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 短链编号
     */
    private String shortLinkNo;

    /**
     * 链接编号
     */
    private String linkNo;

    /**
     * 短链域名
     */
    private String linkDomain;
    /**
     * 手机号码
     */
    private String mobileNo;
    /**
     * URL
     */
    private String url;

    /**
     * 短链类型
     */
    private String shortLineType;

    /**
     * 分表时间
     */
    private String tableTime;

    /**
     * 创建时间
     */
    private Date createTime;

    public static ShortLinkInfoDataBO buildByPO(ShortLinkInfo po) {
        ShortLinkInfoDataBO shortLinkInfoDataBO = new ShortLinkInfoDataBO();
        shortLinkInfoDataBO.setId(po.getId());
        shortLinkInfoDataBO.setShortLinkNo(po.getShortLinkNo());
        shortLinkInfoDataBO.setLinkNo(po.getLinkNo());
        shortLinkInfoDataBO.setMobileNo(po.getMobileNo());
        shortLinkInfoDataBO.setLinkDomain(po.getLinkDomain());
        shortLinkInfoDataBO.setCreateTime(po.getCreateTime());
        shortLinkInfoDataBO.setTableTime(po.getTableTime());
        shortLinkInfoDataBO.setUrl(po.getUrl());
        shortLinkInfoDataBO.setShortLineType(po.getShortLineType());
        return shortLinkInfoDataBO;
    }

//    public static ShortLinkInfoDataBO buildByBO(ShortLinkInfoDataBO bo) {
//        ShortLinkInfoDataBO shortLinkInfoDataBO = new ShortLinkInfoDataBO();
//        shortLinkInfoDataBO.setId(bo.getId());
//        shortLinkInfoDataBO.setShortLinkNo(bo.getShortLinkNo());
//        shortLinkInfoDataBO.setLinkNo(bo.getLinkNo());
//        shortLinkInfoDataBO.setMobileNo(bo.getMobileNo());
//        shortLinkInfoDataBO.setLinkDomain(bo.getLinkDomain());
//        shortLinkInfoDataBO.setCreateTime(bo.getCreateTime());
//        shortLinkInfoDataBO.setTableTime(bo.getTableTime());
//        shortLinkInfoDataBO.setUrl(bo.getUrl());
//        shortLinkInfoDataBO.setShortLineType(bo.getShortLineType());
//        return shortLinkInfoDataBO;
//    }

    public ShortLinkInfo buildPO() {
        ShortLinkInfo shortLinkInfo = new ShortLinkInfo();
        shortLinkInfo.setShortLinkNo(shortLinkNo);
        shortLinkInfo.setLinkNo(linkNo);
        shortLinkInfo.setMobileNo(mobileNo);
        shortLinkInfo.setLinkDomain(linkDomain);
        shortLinkInfo.setTableTime(tableTime);
        return shortLinkInfo;
    }


    public String toInfoCahceStr() {
        if(!StrUtil.isEmpty(url)){
            return StrUtil.format("{}|{}|{}|{}|{}|{}",
                    shortLinkNo,
                    DateUtil.date().getTime() / 1000,
                    mobileNo,
                    linkNo,
                    url,
                    shortLineType
            );
        }
        return StrUtil.format("{}|{}|{}|{}",
                shortLinkNo,
                DateUtil.date().getTime() / 1000,
                mobileNo,
                linkNo
        );
    }

    public String toCahceStr() {
        if(!StrUtil.isEmpty(shortLineType)){
            return StrUtil.format("{}|{}|{}|{}|{}",
                    DateUtil.date().getTime() / 1000,
                    mobileNo,
                    linkNo,
                    StrUtil.isEmpty(url)?"":url,
                    shortLineType
            );
        }

        if(!StrUtil.isEmpty(url)){
            return StrUtil.format("{}|{}|{}|{}",
                    DateUtil.date().getTime() / 1000,
                    mobileNo,
                    linkNo,
                    url
            );
        }

        return StrUtil.format("{}|{}|{}",
                DateUtil.date().getTime() / 1000,
                mobileNo,
                linkNo
                );
    }

    public String toRedisStr() {
        return StrUtil.format("{}|{}|{}|{}",
                mobileNo,
                linkNo,
                StrUtil.isEmpty(url)?"":url,
                shortLineType  == null ? "-1": shortLineType
        );
    }

    public static ShortLinkInfoDataBO buildByInfoCacheStr(String cacheStr) {
        if (StrUtil.isEmpty(cacheStr)) {
            return null;
        }
        String[] split = cacheStr.split("\\|", -1);
        if (split.length < 4) {
            return null;
        }
        String shortLineType = null;
        if (split.length > 4) { //=5可以取4
            shortLineType = split[4];
        }
        return ShortLinkInfoDataBO.builder()
                .shortLinkNo(split[0])
                .mobileNo(split[2])
                .linkNo(split[3])
                .shortLineType(shortLineType)
                .build();
    }

    public static ShortLinkInfoDataBO buildByCacheStr(String cacheStr) {
        if (StrUtil.isEmpty(cacheStr)) {
            return null;
        }
        String[] split = cacheStr.split("\\|");
        if (split.length < 3) {
            return null;
        }
        if(split.length == 4){
            return ShortLinkInfoDataBO.builder()
                    .mobileNo(split[1])
                    .linkNo(split[2])
                    .url(split[3])
                    .build();
        }
        if(split.length == 5){
            return ShortLinkInfoDataBO.builder()
                    .mobileNo(split[1])
                    .linkNo(split[2])
                    .url(split[3])
                    .shortLineType(split[4])
                    .build();
        }
        return ShortLinkInfoDataBO.builder()
                .mobileNo(split[1])
                .linkNo(split[2])
                .build();
    }

    public static ShortLinkInfoDataBO buildByRedisStr(String cacheStr) {
        if (StrUtil.isEmpty(cacheStr)) {
            return null;
        }
        String[] split = cacheStr.split("\\|");
        if (split.length < 2) {
            return null;
        }
        if (split.length == 3){
            return ShortLinkInfoDataBO.builder()
                    .mobileNo(split[0])
                    .linkNo(split[1])
                    .shortLineType(split[2])
                    .build();
        } if (split.length == 4){
            return ShortLinkInfoDataBO.builder()
                    .mobileNo(split[0])
                    .linkNo(split[1])
                    .url(split[2])
                    .shortLineType(split[3])
                    .build();
        }else {
            return ShortLinkInfoDataBO.builder()
                    .mobileNo(split[0])
                    .linkNo(split[1])
                    .build();
        }

    }


}
