package pe.kotlin.mongo.service

import kotlinx.coroutines.flow.Flow
import org.mapstruct.*
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import pe.kotlin.mongo.core.component.OrderComponent
import pe.kotlin.mongo.core.domain.Order
import pe.kotlin.mongo.model.dto.OrderDTO
import pe.kotlin.mongo.support.mapstruct.GenericMapStruct


@Service
class OrderService(private val orderComponent: OrderComponent) {
    suspend fun getAllOrders(): Flow<Order> {
        return orderComponent.getAllOrders()
    }

    suspend fun getOrder(orderNo: String): Order? {
        /*val result: Order? = orderComponent.getOrderById(orderNo)
        result?.let {
            println("order $it")
        }*/
      return orderComponent.getOrderById(orderNo)
    }

    @Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
    )
    interface OrderMapStruct: GenericMapStruct<Order, OrderDTO> {

        companion object {
            val INSTANCE: OrderMapStruct = Mappers.getMapper(OrderMapStruct::class.java)
        }
    }
}
