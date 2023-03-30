package pe.kotlin.mongo.core.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Repository

@Repository
class OrderRepository(private val mongoTemplate: ReactiveMongoTemplate) {
    suspend fun findAllOrders(): Flow<Order> {
       return mongoTemplate.find(Query(), Order::class.java).asFlow()
    }

    suspend fun findOrderById(orderNo: String): Order? {
        return mongoTemplate.findOne(Query().addCriteria(Order::orderNo isEqualTo orderNo), Order::class.java).awaitSingleOrNull()
    }
}