package io.aligm.poc.spring.cloud.sleuth.configuration

import org.springframework.beans.factory.BeanFactory
import org.springframework.cloud.sleuth.instrument.async.LazyTraceExecutor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.ApplicationEventMulticaster
import org.springframework.context.event.SimpleApplicationEventMulticaster
import org.springframework.context.support.AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME
import org.springframework.scheduling.annotation.AsyncConfigurerSupport
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

@Configuration
@EnableAsync
class ThreadConfigs : AsyncConfigurerSupport() {

    private fun executor(beanFactory: BeanFactory): Executor {
        val threadPoolTaskExecutor = ThreadPoolTaskExecutor().apply {
            corePoolSize = 1
            maxPoolSize = 1
            initialize()
        }
        return LazyTraceExecutor(beanFactory, threadPoolTaskExecutor)
    }

    @Bean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME)
    fun applicationEventMulticaster(beanFactory: BeanFactory): ApplicationEventMulticaster {
        return SimpleApplicationEventMulticaster(beanFactory)
            .apply { setTaskExecutor(executor(beanFactory)) }
    }

}