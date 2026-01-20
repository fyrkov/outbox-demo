package io.github.fyrkov.outbox_demo.publisher

import io.github.fyrkov.outbox_demo.domain.OutboxRecord
import io.github.fyrkov.outbox_demo.repository.OutboxRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class Publisher(
    private val outboxRepository: OutboxRepository
) {

    @Scheduled(fixedRateString = "\${outbox.publish.interval}")
    fun publish() {
        val records: List<OutboxRecord> = outboxRepository.selectUnpublished(100)
        // Batch publish records
        println("Published ${records.size} records")
    }
}