package pe.kotlin.mongo.support.config.webclient

import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.http.client.reactive.ReactorResourceFactory
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import java.time.Duration

@Configuration
class WebClientConfig {

    @Bean
    fun resourceFactory() = ReactorResourceFactory().apply {
        isUseGlobalResources = true
    }

    @Bean
    fun webClient(): WebClient {
        val mapper: (HttpClient) -> HttpClient = {
            it.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000)
                .responseTimeout(Duration.ofSeconds(1))
                .doOnConnected { conn ->
                    conn.addHandlerLast(ReadTimeoutHandler(2))
                        .addHandlerLast(WriteTimeoutHandler(2))
                }
        }

        val connector = ReactorClientHttpConnector(resourceFactory(), mapper)

        return WebClient.builder().clientConnector(connector).build()
    }
}