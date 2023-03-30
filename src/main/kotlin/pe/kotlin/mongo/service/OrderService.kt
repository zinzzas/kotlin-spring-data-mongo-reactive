package pe.kotlin.mongo.service

import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service
import pe.kotlin.mongo.core.component.OrderComponent
import pe.kotlin.mongo.core.domain.Order


@Service
class OrderService(private val orderComponent: OrderComponent) {
    suspend fun getAllOrders(): Flow<Order> {
        return orderComponent.getAllOrders()
    }

    suspend fun getOrderById(orderNo: String): Order? {
        /*val result: Order? = orderComponent.getOrderById(orderNo)
        result?.let {
            println("order $it")
        }*/
      return orderComponent.getOrderById(orderNo)
    }
}
