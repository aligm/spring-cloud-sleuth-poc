package io.aligm.poc.spring.cloud.sleuth.controller

import io.aligm.poc.spring.cloud.sleuth.service.NewThreadTracingService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ServiceController(val newThreadTracingService: NewThreadTracingService) {

    @GetMapping
    fun webRequestLogsTracing(): String {
        logger.info("Web request logged")
        return "Check application logs"
    }

    @GetMapping("/executor-service-thread-logs-tracing")
    fun executorServiceThreadLogsTracing(): String {
        logger.info("Executor service thread logs tracing requested")
        newThreadTracingService.executorServiceThreadPrintLogs()
        return "Check application logs"
    }

    companion object {
        private val logger = LoggerFactory.getLogger(ServiceController::class.java)
    }
}