package io.aligm.poc.spring.cloud.sleuth.service

import io.aligm.poc.spring.cloud.sleuth.event.CustomEvent
import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService

@Service
class NewThreadTracingService(
    val executorService: ExecutorService,
    val executor: Executor,
    val applicationEventPublisher: ApplicationEventPublisher
) {

    @EventListener(ApplicationReadyEvent::class)
    fun onApplicationReady() {
        logger.info("On application ready logs tracing")
        applicationEventPublisher.publishEvent(CustomEvent())
    }

    @EventListener
    fun onSomeEventListener(someEvent: CustomEvent) {
        logger.info("On custom event logs tracing")
    }

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