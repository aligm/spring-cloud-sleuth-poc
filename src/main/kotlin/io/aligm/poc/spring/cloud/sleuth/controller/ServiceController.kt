package io.aligm.poc.spring.cloud.sleuth.controller

import io.aligm.poc.spring.cloud.sleuth.service.NewThreadTracingService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ServiceController(val newThreadTracingService: NewThreadTracingService) {

    @GetMapping("/spring-async-thread-logs-tracing")
    fun springAsyncThreadLogsTracing(): String {
        logger.info("Spring async thread logs tracing requested")
        newThreadTracingService.springAsyncThreadPrintLogs()
        return "Check application logs"
    }

    companion object {
        private val logger = LoggerFactory.getLogger(ServiceController::class.java)
    }

}