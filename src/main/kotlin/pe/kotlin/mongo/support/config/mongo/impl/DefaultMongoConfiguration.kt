package pe.kotlin.mongo.support.config.mongo.impl

import com.mongodb.reactivestreams.client.MongoClient
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import pe.kotlin.mongo.support.config.mongo.MongoDBProperties


@Configuration
class DefaultMongoConfiguration(
    var properties: MongoDBProperties,
    @Qualifier("mongoClient") private val mongoClient: MongoClient,
    @Qualifier("mongoConverter") private val mappingMongoConverter: MappingMongoConverter
) : AbstractReactiveMongoConfiguration() {

    override fun getDatabaseName(): String {
        return properties.getInfo(MongoDBProperties.DatabaseType.TEST)?.database.toString()
    }

    override fun reactiveMongoDbFactory(): ReactiveMongoDatabaseFactory {
        return SimpleReactiveMongoDatabaseFactory(mongoClient, databaseName)
    }

    @Primary
    @Bean("defaultMongoTemplate")
    fun reactiveMongoTemplate(): ReactiveMongoTemplate {
        return ReactiveMongoTemplate(reactiveMongoDbFactory(), mappingMongoConverter)
    }
}
