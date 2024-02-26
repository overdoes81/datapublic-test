package com.cty.statistic.framework.apilog.config;

import com.cty.statistic.framework.apilog.core.filter.ApiAccessLogFilter;
import com.cty.statistic.framework.apilog.core.service.ApiAccessLogFrameworkService;
import com.cty.statistic.framework.apilog.core.service.ApiAccessLogFrameworkServiceImpl;
import com.cty.statistic.framework.apilog.core.service.ApiErrorLogFrameworkService;
import com.cty.statistic.framework.apilog.core.service.ApiErrorLogFrameworkServiceImpl;
import com.cty.statistic.framework.common.enums.WebFilterOrderEnum;
import com.cty.statistic.framework.web.config.WebProperties;
import com.cty.statistic.framework.web.config.Data-publicWebAutoConfiguration;
import com.cty.statistic.module.infra.api.logger.ApiAccessLogApi;
import com.cty.statistic.module.infra.api.logger.ApiErrorLogApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;

@AutoConfiguration(after = Data-publicWebAutoConfiguration.class)
public class Data-publicApiLogAutoConfiguration {

    @Bean
    public ApiAccessLogFrameworkService apiAccessLogFrameworkService(ApiAccessLogApi apiAccessLogApi) {
        return new ApiAccessLogFrameworkServiceImpl(apiAccessLogApi);
    }

    @Bean
    public ApiErrorLogFrameworkService apiErrorLogFrameworkService(ApiErrorLogApi apiErrorLogApi) {
        return new ApiErrorLogFrameworkServiceImpl(apiErrorLogApi);
    }

    /**
     * 创建 ApiAccessLogFilter Bean，记录 API 请求日志
     */
    @Bean
    @ConditionalOnProperty(prefix = "data-public.access-log", value = "enable", matchIfMissing = true) // 允许使用 data-public.access-log.enable=false 禁用访问日志
    public FilterRegistrationBean<ApiAccessLogFilter> apiAccessLogFilter(WebProperties webProperties,
                                                                         @Value("${spring.application.name}") String applicationName,
                                                                         ApiAccessLogFrameworkService apiAccessLogFrameworkService) {
        ApiAccessLogFilter filter = new ApiAccessLogFilter(webProperties, applicationName, apiAccessLogFrameworkService);
        return createFilterBean(filter, WebFilterOrderEnum.API_ACCESS_LOG_FILTER);
    }

    private static <T extends Filter> FilterRegistrationBean<T> createFilterBean(T filter, Integer order) {
        FilterRegistrationBean<T> bean = new FilterRegistrationBean<>(filter);
        bean.setOrder(order);
        return bean;
    }

}
