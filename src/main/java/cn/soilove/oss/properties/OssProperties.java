package cn.soilove.oss.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * aliyun oss 配置文件
 *
 * @author: Chen GuoLin
 * @create: 2020-04-07 18:01
 **/
@Data
@ConfigurationProperties(prefix = OssProperties.ALIYUN_OSS_PREFIX )
public class OssProperties {

    public static final String ALIYUN_OSS_PREFIX = "aliyun.oss";

    /**
     * endpoint
     */
    private String endpoint;

    /**
     * bucket
     */
    private String bucket;

    /**
     * accessKeyId
     */
    private String accessKeyId;

    /**
     * accessKeySecret
     */
    private String accessKeySecret;

    /**
     * 前缀
     */
    private String prefix = "http";
}
