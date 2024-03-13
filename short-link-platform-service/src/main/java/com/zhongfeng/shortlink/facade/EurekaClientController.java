package com.zhongfeng.shortlink.facade;

import com.netflix.discovery.DiscoveryManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gan.feng
 */
@RestController
public class EurekaClientController {

    @PostMapping(value = "/eureka/offline")
    public String offline() {
        DiscoveryManager instance = DiscoveryManager.getInstance();
        instance.shutdownComponent();
        return "Eureka下线成功\n";
    }
}
