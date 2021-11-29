package io.aligm.poc.spring.cloud.sleuth.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.concurrent.Executor

@Service
class NewThreadTracingService(
    val executor: Executor
) {

    fun executorThreadPrintLogs() {
        executor.execute {
            logger.info("Executor thread logs tracing")
        }
        logger.info("Main thread logs tracing")
    }

    companion object {
        private val logger = LoggerFactory.getLogger(NewThreadTracingService::class.java)
    }

}