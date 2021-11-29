package io.aligm.poc.spring.cloud.sleuth.service

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class ScheduleService(val newThreadTracingService: NewThreadTracingService) {

    @Scheduled(fixedDelayString = FIVE_SECONDS)
    fun scheduledJobThreadPrintLogs() {
        logger.info("Scheduled job thread logs tracing")
        newThreadTracingService.springScheduleThreadPrintLogs()
    }

    companion object {
        private const val FIVE_SECONDS = "5000"
        private val logger = LoggerFactory.getLogger(NewThreadTracingService::class.java)
    }

}