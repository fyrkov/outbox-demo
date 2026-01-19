package io.github.fyrkov.outbox_demo.repository

import org.jooq.DSLContext
import org.jooq.JSONB
import org.jooq.impl.DSL.field
import org.jooq.impl.DSL.table
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
class OutboxRepository(
    private val dsl: DSLContext,
) {

    fun insert(aggregateType: String, aggregateId: String, payload: String): Long {
        return dsl.insertInto(table("outbox"))
            .set(field("aggregate_type", String::class.java), aggregateType)
            .set(field("aggregate_id", String::class.java), aggregateId)
            .set(field("payload", JSONB::class.java), JSONB.valueOf(payload))
            .set(field("created_at", Instant::class.java), Instant.now())
            .returning(field("id"))
            .fetchSingle()
            .get(field("id"), Long::class.java)
    }
}