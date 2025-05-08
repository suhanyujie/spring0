package com.xs.hello1.conf.tron;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "tron.setting")
public class TronBlockConfigProperties {
    // 服务启动时，选择主网，还是测试网
    // enum：`mainnet`, `testnet`
    private String net;

    // 转账账户的私钥
    private String privateKey;

    // 转账账户的钱包地址
    private String srcAddr;

    // 转账目标账户的钱包地址
    private String destAddr;

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getSrcAddr() {
        return srcAddr;
    }

    public void setSrcAddr(String srcAddr) {
        this.srcAddr = srcAddr;
    }

    public String getDestAddr() {
        return destAddr;
    }

    public void setDestAddr(String destAddr) {
        this.destAddr = destAddr;
    }
}
