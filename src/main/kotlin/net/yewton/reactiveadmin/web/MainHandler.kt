package net.yewton.reactiveadmin.web

import net.yewton.reactiveadmin.model.Item
import net.yewton.reactiveadmin.model.ItemRepository
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.renderAndAwait

data class ItemResponse(val id: Long, val name: String)
data class ItemsResponse(val data: List<ItemResponse>)

@Component
class MainHandler(private val layout: Layout, private val repo: ItemRepository) {
    suspend fun getIndex(request: ServerRequest): ServerResponse {
        return layout("index")
    }

    suspend fun addItems(request: ServerRequest) : ServerResponse {
        val itemsResponse: ItemsResponse = WebClient.create("https://reqres.in/api/").get()
            .uri { builder ->
                builder
                    .path("items")
                    .queryParam("lazy", "1")
                    .build()
            }
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .awaitBody()
        itemsResponse.data.forEach { repo.save(Item(name = it.name, itemId = it.id)) }

        return layout("index", mapOf("message" to Message(
            title = "OK",
            body = "Success!"
        )))
    }

    private suspend fun layout(name: String, model: Map<String, *> = mapOf<String, Nothing>()): ServerResponse {
        return ServerResponse.ok().renderAndAwait(name, model + mapOf("layout" to this.layout))
    }
}
