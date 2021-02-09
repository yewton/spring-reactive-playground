package net.yewton.reactiveadmin.web

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class RouterConfiguration {

    @Bean
    fun mainRouter(handler: MainHandler) = coRouter {
        accept(MediaType.TEXT_HTML).nest {
            GET("/", handler::getIndex)
            POST("/", handler::addItems)
        }
    }
}
