package io.aligm.poc.spring.cloud.sleuth.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.concurrent.ExecutorService

@Service
class NewThreadTracingService(val executorService: ExecutorService) {

    fun executorServiceThreadPrintLogs() {
        executorService.execute {
            logger.info("Thread logs tracing")
        }
        logger.info("Main thread logs tracing")
    }

    companion object {
        private val logger = LoggerFactory.getLogger(NewThreadTracingService::class.java)
    }

}