package io.aligm.poc.spring.cloud.sleuth.event

import org.springframework.context.ApplicationEvent

class CustomEvent: ApplicationEvent("eventSource")