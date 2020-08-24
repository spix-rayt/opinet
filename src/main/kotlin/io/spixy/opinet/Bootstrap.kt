package io.spixy.opinet

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class Bootstrap

fun main(args: Array<String>) {
    runApplication<Bootstrap>(*args)
}