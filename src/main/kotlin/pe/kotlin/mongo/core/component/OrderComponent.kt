package pe.kotlin.mongo.core.component

import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Component
import pe.kotlin.mongo.core.domain.Order
import pe.kotlin.mongo.core.repository.OrderRepository


@Component
class OrderComponent(private val orderRepository: OrderRepository) {
    suspend fun getAllOrders(): Flow<Order> = orderRepository.findAllOrders()
    suspend fun getOrderById(orderNo: String): Order? = orderRepository.findOneById(orderNo)
}
