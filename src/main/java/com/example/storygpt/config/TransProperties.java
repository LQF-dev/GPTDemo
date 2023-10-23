package com.example.storygpt.config;
 
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
 
/**
 * 用户配置信息类
 * @author luoqinfneg
 **/
@Data
@Component
@ConfigurationProperties(prefix = "youdao")
public class TransProperties
{
    /**
     * app-key
     */
    private String appKey;
 
    /**
     * app-secret
     */
    private String appSecret;
 
    /**
     * from 翻译来源
     */
    private String from;
 
    /**
     * to 翻译目标
     */
    private String to;

}