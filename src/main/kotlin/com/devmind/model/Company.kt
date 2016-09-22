package com.devmind.model

import org.jetbrains.exposed.sql.Table

/**
 * @author Dev-Mind <guillaume@dev-mind.fr>
 * @since 21/09/16.
 */
class Company(
        var name: String,
        var id: Int? = null,
        var workers : MutableList<Worker> = mutableListOf()
)

object Companies : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val name = varchar("name", 50)
}