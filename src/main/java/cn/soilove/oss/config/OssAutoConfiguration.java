package cn.soilove.oss.config;

import cn.soilove.oss.properties.OssProperties;
import cn.soilove.oss.service.OssService;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * aliyun oss 自动配置类
 *
 * @author: Chen GuoLin
 * @create: 2020-04-07 18:00
 **/
@Slf4j(topic = "[starter][oss]")
@Configuration
@ConditionalOnClass(OssService.class)
@EnableConfigurationProperties(OssProperties.class)
public class OssAutoConfiguration {

    @Resource
    private OssProperties ossProperties;

    @Bean
    @ConditionalOnMissingBean
    public OSS ossClient() {
        // 检查参数
        if (StringUtils.isEmpty(ossProperties.getAccessKeyId())
                || StringUtils.isEmpty(ossProperties.getAccessKeySecret())
                || StringUtils.isEmpty(ossProperties.getBucket())
                || StringUtils.isEmpty(ossProperties.getEndpoint())) {
            log.error("OSS配置缺失，请补充!");
            throw new OssStarterException("[starter][oss]OSS配置缺失，请补充!");
        }

        OSS ossClient = new OSSClientBuilder().build(ossProperties.getEndpoint(), ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());
        log.info("OSS配置成功，Bucket=" + ossProperties.getBucket() + "，Endpoint=" + ossProperties.getEndpoint());
        return ossClient;
    }

}
