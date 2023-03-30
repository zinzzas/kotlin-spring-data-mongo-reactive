package pe.kotlin.mongo.service

import io.kotest.core.annotation.Tags
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.last
import org.springframework.boot.test.context.SpringBootTest
import pe.kotlin.mongo.core.domain.Order

@SpringBootTest
class OrderServiceTest(private val orderService: OrderService): DescribeSpec() {

    init {
        Tags("integration")

        describe("OrderService 조회") {
            context("getAllOrders") {
                it("결과 조회") {
                    val result: Flow<Order> = orderService.getAllOrders()

                    result.shouldNotBeNull()

                    result.collect {
                        println("Order >> $it")
                    }
                }
            }
        }
    }
}