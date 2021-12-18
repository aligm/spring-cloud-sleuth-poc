package io.aligm.poc.spring.cloud.sleuth.scheduler

import org.quartz.Job
import org.quartz.JobExecutionContext
import org.slf4j.LoggerFactory

class TracedLoggingJob: Job {

    override fun execute(context: JobExecutionContext) {
        logger.info("Quartz scheduled job execution")
    }

    companion object {
        private val logger = LoggerFactory.getLogger(TracedLoggingJob::class.java)
    }

}