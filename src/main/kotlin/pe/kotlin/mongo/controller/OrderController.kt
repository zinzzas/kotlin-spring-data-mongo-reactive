package pe.kotlin.mongo.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pe.kotlin.mongo.service.OrderService


@RequestMapping("/order")
@RestController
class OrderController(val orderService: OrderService) {

    @GetMapping("/allOrders")
    suspend fun getAllOrders(): ResponseEntity<Any> {
        return ResponseEntity<Any>(orderService.getAllOrders(), HttpStatus.OK)
    }

    @GetMapping("/getOrderByOrderNo/{orderNo}")
    suspend fun getOrderById(@PathVariable orderNo: String): ResponseEntity<Any>? {
        return ResponseEntity<Any>(orderService.getOrderById(orderNo), HttpStatus.OK)
    }
}
