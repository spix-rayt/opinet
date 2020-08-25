package io.spixy.opinet.app.view

import com.vaadin.flow.component.UI
import com.vaadin.flow.component.login.LoginForm
import com.vaadin.flow.component.orderedlayout.FlexComponent
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.router.Route
import io.spixy.opinet.app.getAuth
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder

@Route(value = "login", layout = MainView::class)
class LoginView : HorizontalLayout() {
    private val logger = KotlinLogging.logger {}

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    init {
        if(getAuth() == null) {
            UI.getCurrent().navigate(HomeView::class.java)
        }

        val loginForm = LoginForm()
        loginForm.isForgotPasswordButtonVisible = false

        loginForm.addLoginListener { event ->
            try {
                val a = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(event.username, event.password))

                SecurityContextHolder.getContext().authentication = a

                UI.getCurrent().navigate(HomeView::class.java)
            } catch (authenticationException: AuthenticationException) {
                loginForm.isError = true
            }
        }

        justifyContentMode = FlexComponent.JustifyContentMode.CENTER
        alignItems = FlexComponent.Alignment.CENTER

        add(loginForm)
        setSizeFull()
    }
}