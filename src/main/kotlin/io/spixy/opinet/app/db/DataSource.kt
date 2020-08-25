package io.spixy.opinet.app.db

import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.sql.Connection

object Users : IntIdTable() {
    val login = varchar("name", 1000)
    val password = varchar("password", 1000)
}

class User(id: EntityID<Int>) : IntEntity(id) {
    companion object : EntityClass<Int, User>(Users)

    var login by Users.login
    var password by Users.password
}

object AccessFlags : IntIdTable() {
    val name = varchar("name", 1000)
    val userId = reference ("user_id", Users)
}

@Component
@Order(1)
@Scope("singleton")
class DataSource {
    init {
        Database.connect("jdbc:sqlite:db.sqlite", "org.sqlite.JDBC")
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE

        transaction {
            SchemaUtils.create(Users, AccessFlags)
        }
    }
}