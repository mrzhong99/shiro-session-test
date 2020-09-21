package com.zhongpeiqi.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {
    private String host;
    private Integer port;
}
