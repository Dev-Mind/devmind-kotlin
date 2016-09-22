package com.devmind.model

import org.jetbrains.exposed.sql.Table

/**
 * @author Dev-Mind <guillaume@dev-mind.fr>
 * @since 21/09/16.
 */
data class Worker(
        var firstname: String,
        var lastname: String,
        var company: Company,
        var id: Int? = null
)

object Workers : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val firstname = varchar("firstname", length = 150)
    val lastname = varchar("lastname", length = 150)
    val companyId = integer("company_id") references Companies.id
}