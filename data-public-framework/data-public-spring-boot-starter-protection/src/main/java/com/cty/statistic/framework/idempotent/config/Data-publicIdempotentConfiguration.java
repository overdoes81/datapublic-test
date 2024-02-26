package com.cty.statistic.framework.idempotent.config;

import com.cty.statistic.framework.idempotent.core.aop.IdempotentAspect;
import com.cty.statistic.framework.idempotent.core.keyresolver.impl.DefaultIdempotentKeyResolver;
import com.cty.statistic.framework.idempotent.core.keyresolver.impl.ExpressionIdempotentKeyResolver;
import com.cty.statistic.framework.idempotent.core.keyresolver.IdempotentKeyResolver;
import com.cty.statistic.framework.idempotent.core.redis.IdempotentRedisDAO;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import com.cty.statistic.framework.redis.config.Data-publicRedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

@AutoConfiguration(after = Data-publicRedisAutoConfiguration.class)
public class Data-publicIdempotentConfiguration {

    @Bean
    public IdempotentAspect idempotentAspect(List<IdempotentKeyResolver> keyResolvers, IdempotentRedisDAO idempotentRedisDAO) {
        return new IdempotentAspect(keyResolvers, idempotentRedisDAO);
    }

    @Bean
    public IdempotentRedisDAO idempotentRedisDAO(StringRedisTemplate stringRedisTemplate) {
        return new IdempotentRedisDAO(stringRedisTemplate);
    }

    // ========== 各种 IdempotentKeyResolver Bean ==========

    @Bean
    public DefaultIdempotentKeyResolver defaultIdempotentKeyResolver() {
        return new DefaultIdempotentKeyResolver();
    }

    @Bean
    public ExpressionIdempotentKeyResolver expressionIdempotentKeyResolver() {
        return new ExpressionIdempotentKeyResolver();
    }

}
