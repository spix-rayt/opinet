package io.spixy.opinet

import com.vaadin.flow.component.applayout.AppLayout
import com.vaadin.flow.router.Route
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

@Route
class MainView : AppLayout() {
    init {
        logger.info { "init" }
    }
}