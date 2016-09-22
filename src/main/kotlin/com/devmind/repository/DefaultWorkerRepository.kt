package com.devmind.repository

import com.devmind.model.Worker
import com.devmind.model.Workers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

/**
 * This repository manage a Company
 */
interface WorkerRepository : CrudRepository<Worker, Int>

@Repository
@Transactional
open class DefaultWorkerRepository(val companyRepository: CompanyRepository) : WorkerRepository {

    override fun createTable() = SchemaUtils.create(Workers);

    override fun create(worker: Worker): Worker {
        worker.id = Workers.insert(toRow(worker)).generatedKey
        return worker
    }

    override fun update(id: Int, worker: Worker): Int = Workers.update({Workers.id eq id}) { toRow(worker) }


    override fun findById(id: Int): Worker = Workers.select({Workers.id eq id}).map { fromRow(it) }.first()

    override fun findAll(): Iterable<Worker> = Workers.selectAll().map { fromRow(it) }

    override fun deleteAll() = Workers.deleteAll()

    private fun toRow(worker: Worker): Workers.(UpdateBuilder<*>) -> Unit = {
        it[firstname] = worker.firstname
        it[lastname] = worker.lastname
        it[companyId] = worker.company?.id
        if (worker.id != null) it[id] = worker.id
    }

    private fun fromRow(result: ResultRow) =
            Worker(result[Workers.firstname],
                    result[Workers.lastname],
                    companyRepository.findById(result[Workers.companyId]),
                    result[Workers.id])

}