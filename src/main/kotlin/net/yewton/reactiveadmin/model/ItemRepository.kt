package net.yewton.reactiveadmin.model

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository // make IntelliJ happy
interface ItemRepository: CoroutineCrudRepository<Item, Long>
