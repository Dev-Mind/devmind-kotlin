package com.devmind.repository

import com.devmind.model.Companies
import com.devmind.model.Company
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

/**
 * This repository manage a Company
 */
interface CompanyRepository : CrudRepository<Company, Int>

@Repository
@Transactional
open class DefaultCompanyRepository : CompanyRepository {

    override fun createTable() = SchemaUtils.create(Companies);

    override fun create(company: Company): Company {
        company.id = Companies.insert(toRow(company)).generatedKey
        return company
    }

    override fun update(id: Int, company: Company): Int = Companies.update({ Companies.id eq id}) { toRow(company) }


    override fun findById(id: Int): Company = Companies.select({ Companies.id eq id}).map { fromRow(it) }.first()

    override fun findAll(): Iterable<Company> = Companies.selectAll().map { fromRow(it) }

    override fun deleteAll() = Companies.deleteAll()

    private fun toRow(company: Company): Companies.(UpdateBuilder<*>) -> Unit = {
        it[name] = company.name
        if (company.id != null) it[id] = company.id
    }

    private fun fromRow(result: ResultRow) =
            Company(result[Companies.name],
                    result[Companies.id])

}