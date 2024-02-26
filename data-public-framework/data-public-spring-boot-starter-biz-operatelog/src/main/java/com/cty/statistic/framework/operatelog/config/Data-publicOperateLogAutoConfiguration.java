package com.cty.statistic.framework.operatelog.config;

import com.cty.statistic.framework.operatelog.core.aop.OperateLogAspect;
import com.cty.statistic.framework.operatelog.core.service.OperateLogFrameworkService;
import com.cty.statistic.framework.operatelog.core.service.OperateLogFrameworkServiceImpl;
import com.cty.statistic.module.system.api.logger.OperateLogApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class Data-publicOperateLogAutoConfiguration {

    @Bean
    public OperateLogAspect operateLogAspect() {
        return new OperateLogAspect();
    }

    @Bean
    public OperateLogFrameworkService operateLogFrameworkService(OperateLogApi operateLogApi) {
        return new OperateLogFrameworkServiceImpl(operateLogApi);
    }

}
