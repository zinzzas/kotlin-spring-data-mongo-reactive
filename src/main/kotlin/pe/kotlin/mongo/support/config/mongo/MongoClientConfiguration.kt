package pe.kotlin.mongo.support.config.mongo

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@Configuration
@EnableConfigurationProperties(MongoDBProperties::class)

class MongoClientConfiguration(var properties: MongoDBProperties) : MongoConfiguration {

    @Bean("mongoClient")
    fun mongoClient(properties: MongoDBProperties): MongoClient {
        return MongoClients.create(mongoClientSettings(properties.uri, defaultPoolSettings()))
    }
}