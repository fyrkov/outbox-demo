package io.github.fyrkov.outbox_demo.domain

data class OutboxRecord(
    val id: Long,
    val aggregateType: String,
    val aggregateId: String,
    val payload: String,
    val createdAt: String,
    val publishedAt: String? = null
)