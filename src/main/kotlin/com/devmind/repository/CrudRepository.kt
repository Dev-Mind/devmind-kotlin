package com.devmind.repository

/**
 * @author Dev-Mind <guillaume@dev-mind.fr>
 * @since 21/09/16.
 */

interface CrudRepository<T, K> {
    fun createTable()
    fun create(m: T): T
    fun update(id: K, m: T): K
    fun findById(id: K): T
    fun findAll(): Iterable<T>
    fun deleteAll(): Int
}
