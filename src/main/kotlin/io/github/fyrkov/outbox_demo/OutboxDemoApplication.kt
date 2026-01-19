package io.github.fyrkov.outbox_demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OutboxDemoApplication

fun main(args: Array<String>) {
	runApplication<OutboxDemoApplication>(*args)
}
