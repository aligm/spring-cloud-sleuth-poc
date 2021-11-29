package io.aligm.poc.spring.cloud.sleuth.service

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService

@Service
class NewThreadTracingService(
    val executorService: ExecutorService,
    val executor: Executor
) {

    fun executorServiceThreadPrintLogs() {
        executorService.execute {
            logger.info("Thread logs tracing")
        }
        logger.info("Main thread logs tracing")
    }

    fun executorThreadPrintLogs() {
        executor.execute {
            logger.info("Executor thread logs tracing")
        }
        logger.info("Main thread logs tracing")
    }

    @Async
    fun springAsyncThreadPrintLogs() {
        logger.info("Spring async thread logs tracing")
    }

    companion object {
        private val logger = LoggerFactory.getLogger(NewThreadTracingService::class.java)
    }

}