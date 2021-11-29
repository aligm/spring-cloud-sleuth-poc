package io.aligm.poc.spring.cloud.sleuth.controller

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ServiceController {

    @GetMapping
    fun webRequestLogsTracing(): String {
        logger.info("Web request logged")
        return "Check application logs"
    }

    companion object {
        private val logger = LoggerFactory.getLogger(ServiceController::class.java)
    }
}