package pe.kotlin.mongo.core.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import pe.kotlin.mongo.support.config.mongo.MongoCollection
import pe.kotlin.mongo.support.extension.NoArg

@NoArg
@Document(collection = MongoCollection.ORDER)
data class Order(

    @Id
    val id: String,
    val orderNo: String,
    val customer: Customer?,
    val customerNo: String,
    val orderSheet: OrderSheet?,
    val orderMediaCode: String,
    val orderPocType: String,
    val insertId: String,
    @CreatedDate val insertDate: String,
    val updateId: String,
    @CreatedDate val updateDate: String,

    ) {
    data class Customer(
        val customerBase: CustomerBase?
    ) {
        data class CustomerBase(
            val customerNo: String,
            val customerType: String,
            val customerGradeCode: String,
            val emailAddress: String,
            val genderType: String,
            val phoneNumber: String
        )
    }

    data class OrderSheet(
        val orderBase: OrderBase?,
        val orderDeliverys: List<OrderDelivery> = mutableListOf(),
        val orderDetails: List<OrderDetail> = mutableListOf(),
        val orderPays: List<OrderPay> = mutableListOf(),
    ) {
        data class OrderBase(
            val customerMessage: String,
            val deliveryPayAmount: Int,
            val orderPayAmount: Int
        )

        data class OrderDelivery(
            val deliverySeq: Int,
            val deliveryName: String,
            val deliveryShipCode: String,
            val receiverName: String,
            val receiverPhoneNumber: String,
            val receiverPostNo: Int,
            val receiverPostAddress: String,
            val receiverDetailAddress: String,
        )

        data class OrderDetail(
            val cartNo: String,
            val deliveryPolicyNo: String,
            val deliverySeq: String,
            val discountPrice: Int,
            val itemNo: String,
            val orderProductSeq: Int,
            val orderQty: Int,
            val productNo: String,
            val salePrice: Int
        )

        data class OrderPay(
            val payProcSeq: Int,
            val payTotalAmount: Int,
            val orderPay: OrderPay?,
        ) {
            data class OrderPay(
                val orderPayAmount: Int,
                val payMethod: String,
                val remainAmount: Int,
            )
        }
    }
}