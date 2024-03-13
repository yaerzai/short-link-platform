package com.zhongfeng.shortlink.config;

import cn.hutool.core.net.Ipv4Util;
import com.zhongfeng.sms.persistence.dataservice.SysIpConfigDataService;
import com.zhongfeng.sms.persistence.dataservice.bo.SysIpConfigDataBO;
import com.zhongfeng.sms.persistence.dataservice.bo.SysIpConfigQueryBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
@Slf4j
public class IPInitConfig {
    @Resource
    private SysIpConfigDataService sysIpConfigDataService;
    public static SysIpConfigDataBO[] ipBeans = null;

    @PostConstruct
    public void init() {
        SysIpConfigQueryBO queryBO = new SysIpConfigQueryBO();
        queryBO.setPageSize(Integer.MAX_VALUE);
        List<SysIpConfigDataBO> ipConfigDataBOList = sysIpConfigDataService.query(queryBO).getData();
        Collections.sort(ipConfigDataBOList, Comparator.comparingLong(SysIpConfigDataBO::getNumStart));
        ipBeans = ipConfigDataBOList.toArray(new SysIpConfigDataBO[]{});
        log.warn("IPInitConfig init success {}", ipBeans.length);
//        SortedMap<Long, IPData> sortedMap = new TreeMap<Long, IPData>();
//        ipConfigDataBOList.forEach(item -> sortedMap.put(item.getNumStart(), IPData.buildData(item)));
    }

//    public static void main(String[] args) {
//        SysIpConfigDataBO ipData = new SysIpConfigDataBO();
//        ipData.setIpStart("223.208.0.0");
//        ipData.setIpEnd("223.208.55.255");
//        ipData.setNumStart(3754950656L);
//        ipData.setNumEnd(3754964991L);
//        ipData.setRegion("重庆市");
//        ipData.setCity("重庆市");
//        SysIpConfigDataBO ipData2 = new SysIpConfigDataBO();
//        ipData2.setIpStart("223.214.136.0");
//        ipData2.setIpEnd("223.214.141.255");
//        ipData2.setNumStart(3755378688L);
//        ipData2.setNumEnd(3755380223L);
//        ipData2.setRegion("安徽省");
//        ipData2.setCity("宿州市");
//        ipData2.setArea("灵璧县");
////        SortedMap<Long, IPData> sortedMap = new TreeMap<Long, IPData>();
////        sortedMap.put(ipData.getNumStart(), ipData);
////        sortedMap.put(ipData2.getNumStart(), ipData2);
////        String requestIp = "223.214.156.110";
////        long targetIPNum = Ipv4Util.ipv4ToLong(requestIp);
////        SortedMap<Long, IPData> subMap = sortedMap.headMap(targetIPNum);
////        if (subMap != null && subMap.size() > 0) {
////            long key = subMap.lastKey();
////            IPData suIpData = subMap.get(key);
////            System.out.println(suIpData.toString());
////        }
//        List<SysIpConfigDataBO> ipConfigDataBOList = new ArrayList<>();
//        ipConfigDataBOList.add(ipData);
//        ipConfigDataBOList.add(ipData2);
//        Collections.sort(ipConfigDataBOList, Comparator.comparingLong(SysIpConfigDataBO::getNumStart));
//        ipBeans = ipConfigDataBOList.toArray(new SysIpConfigDataBO[]{});
//        String requestIp = "223.208.55.255";
//        SysIpConfigDataBO sysIpConfigDataBO = getIpByHalf(requestIp);
//        System.out.println(sysIpConfigDataBO.toString());
//    }

    /**
     * 根据ip地址判断属于哪个ip地址段，返回这个段的ip地址对象
     * 使用二分查找算法来实现
     *
     * @param requestIp
     * @return
     */
    public static SysIpConfigDataBO getIpByHalf(String requestIp) {
        SysIpConfigDataBO[] ipaBeans = ipBeans;
        if (ipaBeans == null || ipaBeans.length == 0) {
            return null;
        }
        long iplong = Ipv4Util.ipv4ToLong(requestIp);
        if (iplong < ipaBeans[0].getNumStart() || iplong > ipaBeans[ipaBeans.length - 1].getNumEnd()) {
            return null;
        }
        int low = 0;
        int high = ipaBeans.length - 1;
        //不能使用 int mid = (left + right) / 2,如果 low 和 high 比较大的话，两者之和就有可能会溢出
        int mid = low + ((high - low) >>> 1);
        //当两个指针同时指向同一个数据时,需用 = 判断
        while (low <= high) {
            if (iplong < ipaBeans[mid].getNumStart()) {
                high = mid - 1;
            } else if (iplong > ipaBeans[mid].getNumStart()) {
                low = mid + 1;
            }
            if (iplong >= ipaBeans[mid].getNumStart() && iplong <= ipaBeans[mid].getNumEnd()) {
                return ipaBeans[mid];
            }
            mid = (low + high) / 2;
        }
        return null;
    }
}
