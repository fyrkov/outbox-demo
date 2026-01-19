package io.github.fyrkov.outbox_demo.repository

import io.github.fyrkov.outbox_demo.AbstractIntegrationTest
import org.jooq.DSLContext
import org.jooq.impl.DSL.field
import org.jooq.impl.DSL.table
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class OutboxRepositoryIntegrationTest @Autowired constructor(
    private val outboxRepository: OutboxRepository,
    private val dsl: DSLContext,
) : AbstractIntegrationTest() {

    @Test
    fun `should insert a record in the outbox`() {
        // given
        val aggregateType = "account"
        val aggregateId = "123"
        val payload = """{"balance":100}"""

        // when
        val id = outboxRepository.insert(aggregateType, aggregateId, payload)

        // then
        val count = dsl.selectCount().from(table("outbox")).fetchOne(0, Int::class.java)
        assertEquals(1, count)

        val record = dsl.selectFrom(table("outbox")).where(field("id").eq(id)).fetchSingle()
        assertEquals(id, record.get(field("id", Long::class.java)))
        assertEquals(aggregateType, record.get(field("aggregate_type", String::class.java)))
        assertEquals(aggregateId, record.get(field("aggregate_id", String::class.java)))
        assertEquals(payload, record.get(field("payload")).toString())
        assertNotNull(record.get(field("created_at")))
        assertNull(record.get(field("published_at")))
    }
}