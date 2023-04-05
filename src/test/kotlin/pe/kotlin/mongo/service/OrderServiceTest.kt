package pe.kotlin.mongo.service

import io.kotest.core.annotation.Tags
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.springframework.boot.test.context.SpringBootTest
import pe.kotlin.mongo.model.dto.OrderDTO

@SpringBootTest
class OrderServiceTest(private val orderService: OrderService): DescribeSpec() {

    init {
        Tags("integration")

        describe("OrderService 조회") {
            context("getAllOrders") {
                it("결과 조회") {
                    val result: List<OrderDTO> = orderService.getAllOrders().map {
                        OrderService.OrderMapStruct.INSTANCE.toDTO(it)
                    }.toList()

                    result.shouldNotBeNull()

                    println("Order >> $result")
                }
            }
        }
    }
}