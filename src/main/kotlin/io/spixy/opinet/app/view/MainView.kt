package io.spixy.opinet.app.view

import com.vaadin.flow.component.UI
import com.vaadin.flow.component.applayout.AppLayout
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.orderedlayout.FlexComponent
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import io.spixy.opinet.app.getAuth
import mu.KotlinLogging

class MainView : AppLayout() {
    private val logger = KotlinLogging.logger {}

    init {
        val startItemsLayout = HorizontalLayout()
        startItemsLayout.alignItems = FlexComponent.Alignment.START
        startItemsLayout.setWidthFull()

        val endItemsLayout = HorizontalLayout()
        endItemsLayout.alignItems = FlexComponent.Alignment.END

        if(getAuth() == null) {
            val loginButton = Button("Войти")

            loginButton.addClickListener {
                UI.getCurrent().navigate(LoginView::class.java)
            }

            endItemsLayout.add(loginButton)
        }

        addToNavbar(startItemsLayout, endItemsLayout)
    }
}