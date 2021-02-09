package net.yewton.reactiveadmin.model

import org.springframework.data.annotation.Id
import java.time.LocalDateTime

data class Item(@Id val id: Long = 0,
                val itemId: Long,
                val name: String,
                val createdDate: LocalDateTime = LocalDateTime.now())
