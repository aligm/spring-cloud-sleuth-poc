package io.aligm.poc.spring.cloud.sleuth.configuration

import org.springframework.beans.factory.BeanFactory
import org.springframework.cloud.sleuth.instrument.async.LazyTraceExecutor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

@Configuration
class ThreadConfigs {

    @Bean
    fun executor(beanFactory: BeanFactory): Executor {
        val threadPoolTaskExecutor = ThreadPoolTaskExecutor().apply {
            corePoolSize = 1
            maxPoolSize = 1
            initialize()
        }
        return LazyTraceExecutor(beanFactory, threadPoolTaskExecutor)
    }

}