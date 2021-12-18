package io.aligm.poc.spring.cloud.sleuth.configuration

import io.aligm.poc.spring.cloud.sleuth.scheduler.TracedLoggingJob
import org.quartz.JobDetail
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.quartz.JobDetailFactoryBean
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean

@Configuration
@EnableScheduling
class ScheduleConfigs {

    @Bean
    fun jobDetail(): JobDetailFactoryBean = JobDetailFactoryBean().apply {
        setJobClass(TracedLoggingJob::class.java)
        setDescription("Launching traced logging job...")
        setDurability(true)
    }

    @Bean
    fun jobTrigger(jobDetail: JobDetail): SimpleTriggerFactoryBean = SimpleTriggerFactoryBean().apply {
        setJobDetail(jobDetail)
        setRepeatInterval(FIVE_SECONDS)
        setRepeatCount(3)
    }

    companion object {
        private const val FIVE_SECONDS = 5000L
    }

}