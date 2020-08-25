package io.spixy.opinet.app

import io.spixy.opinet.app.db.User
import io.spixy.opinet.app.db.Users
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class DataService {
    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    fun auth(login: String, password: String): User? = transaction {
        User.find { Users.login eq login }.firstOrNull()?.takeIf { passwordEncoder.matches(password, it.password) }
    }
}