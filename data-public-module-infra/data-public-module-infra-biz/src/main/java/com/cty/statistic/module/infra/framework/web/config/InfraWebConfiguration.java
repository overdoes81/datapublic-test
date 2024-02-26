package com.cty.statistic.module.infra.framework.web.config;

import com.cty.statistic.framework.swagger.config.Data-publicSwaggerAutoConfiguration;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * infra 模块的 web 组件的 Configuration
 *
 * @author 芋道源码
 */
@Configuration(proxyBeanMethods = false)
public class InfraWebConfiguration {

    /**
     * infra 模块的 API 分组
     */
    @Bean
    public GroupedOpenApi infraGroupedOpenApi() {
        return Data-publicSwaggerAutoConfiguration.buildGroupedOpenApi("infra");
    }

}
