package io.spixy.opinet.app.view

import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.html.Label
import com.vaadin.flow.router.Route
import io.spixy.opinet.app.getAuth

@Route(value = "", layout = MainView::class)
class HomeView : Div() {

    init {
        val label = Label()

        val auth = getAuth()
        if(auth != null) {
            label.text = "Hello, ${auth.principal}"
        } else {
            label.text = "Hello, Noname :p"
        }

        add(label)
    }
}