package io.aligm.poc.spring.cloud.sleuth.service

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class NewThreadTracingService {

    @Async
    fun springAsyncThreadPrintLogs() {
        logger.info("Spring async thread logs tracing")
    }

    companion object {
        private val logger = LoggerFactory.getLogger(NewThreadTracingService::class.java)
    }

}