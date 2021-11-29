package io.aligm.poc.spring.cloud.sleuth.service

import io.aligm.poc.spring.cloud.sleuth.event.CustomEvent
import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class NewThreadTracingService(
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

    companion object {
        private val logger = LoggerFactory.getLogger(NewThreadTracingService::class.java)
    }

}