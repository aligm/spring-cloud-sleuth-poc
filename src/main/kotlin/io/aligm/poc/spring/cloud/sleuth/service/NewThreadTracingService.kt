package io.aligm.poc.spring.cloud.sleuth.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class NewThreadTracingService {

    fun springScheduleThreadPrintLogs() {
        logger.info("Spring schedule thread logs tracing")
    }

    companion object {
        private val logger = LoggerFactory.getLogger(NewThreadTracingService::class.java)
    }

}