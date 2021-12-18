package io.aligm.poc.spring.cloud.sleuth.configuration

import io.aligm.poc.spring.cloud.sleuth.scheduler.TracedLoggingJob
import org.quartz.JobBuilder
import org.quartz.JobDetail
import org.quartz.ScheduleBuilder
import org.quartz.Scheduler
import org.quartz.SimpleScheduleBuilder
import org.quartz.SimpleTrigger
import org.quartz.Trigger
import org.quartz.TriggerBuilder
import org.quartz.impl.StdSchedulerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling

@Configuration
@EnableScheduling
class ScheduleConfigs {

    @Bean
    fun jobDetail(): JobDetail =
        JobBuilder.newJob().apply {
            ofType(TracedLoggingJob::class.java)
            withIdentity("Logging job")
            withDescription("Launching traced logging job...")
            storeDurably()
        }.build()

    @Bean
    fun jobScheduleBuilder(): ScheduleBuilder<SimpleTrigger> =
        SimpleScheduleBuilder.simpleSchedule().apply {
            withRepeatCount(3)
            withIntervalInSeconds(5)
        }

    @Bean
    fun jobTrigger(jobDetail: JobDetail, jobScheduleBuilder: ScheduleBuilder<SimpleTrigger>): Trigger =
        TriggerBuilder.newTrigger().apply {
            withIdentity("Logging job trigger")
            forJob(jobDetail)
            withSchedule(jobScheduleBuilder)
            startNow()
        }.build()

    @Bean
    fun scheduler(jobDetail: JobDetail, trigger: Trigger): Scheduler =
        StdSchedulerFactory().scheduler.apply {
            addJob(jobDetail, true)
            scheduleJob(trigger)
            start()
        }

}