package pe.kotlin.mongo.core.repository

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.core.io.ResourceLoader
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Repository
import pe.kotlin.mongo.core.domain.Order

@Repository
class OrderRepository(
    private val mongoTemplate: ReactiveMongoTemplate,
    private val resourceLoader: ResourceLoader
) {
    suspend fun findAllOrders(): Flow<Order> {
        val resource = resourceLoader.getResource("classpath:dummy/order-dummy.json")
        val jsonContent = resource.inputStream.reader().use { it.readText() }
        val orders = ObjectMapper().readValue<List<Order>>(jsonContent)
        return orders.asFlow()
       //return mongoTemplate.find(Query(), Order::class.java).asFlow()
    }

    suspend fun findOneById(orderNo: String): Order? {
        return mongoTemplate.findOne(Query().addCriteria(Order::orderNo isEqualTo orderNo), Order::class.java).awaitSingleOrNull()
    }
}