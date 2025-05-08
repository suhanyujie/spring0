package com.xs.hello1.conf.tron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(TronBlockConfigProperties.class)
public class TronBlockConfig {
    // 使用的 tron 网络
    @Value("${tron.setting.net}")
    private String tronNet;

    // 主账户的私钥
    @Value("${tron.setting.privateKey}")
    private String privateKey;

    // 主账户的钱包地址
    @Value("${tron.setting.srcAddr}")
    private String srcAddr;

    // 转账的目标账户钱包地址
    @Value("${tron.setting.destAddr}")
    private String destAddr;

    @Autowired
    private TronBlockConfigProperties tronBlockConfigProperties;
}
