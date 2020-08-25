package io.spixy.opinet

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [ErrorMvcAutoConfiguration::class])
open class Bootstrap

fun main(args: Array<String>) {
    runApplication<Bootstrap>(*args)
}