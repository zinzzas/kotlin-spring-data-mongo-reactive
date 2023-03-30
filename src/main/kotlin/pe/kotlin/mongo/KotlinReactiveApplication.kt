package pe.kotlin.mongo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinReactiveApplication

fun main(args: Array<String>) {
    runApplication<KotlinReactiveApplication>(*args)
}
