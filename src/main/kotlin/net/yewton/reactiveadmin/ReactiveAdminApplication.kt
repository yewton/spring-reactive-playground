package net.yewton.reactiveadmin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReactiveAdminApplication

fun main(args: Array<String>) {
	runApplication<ReactiveAdminApplication>(*args)
}
