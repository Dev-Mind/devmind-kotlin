package com.devmind

import com.devmind.model.Company
import com.devmind.model.Worker
import com.devmind.repository.CompanyRepository
import com.devmind.repository.WorkerRepository
import org.jetbrains.exposed.spring.SpringTransactionManager
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@SpringBootApplication
@EnableTransactionManagement
open class DevmindKotlinApplication{

    @Bean
    open fun transactionManager(dataSource: DataSource) = SpringTransactionManager(dataSource)

    @Bean
    open fun init(companyRepository: CompanyRepository, workerRepository: WorkerRepository) = CommandLineRunner {
        workerRepository.createTable()
        workerRepository.deleteAll()

        companyRepository.createTable()
        companyRepository.deleteAll()

        val company = companyRepository.create(Company("Dev-Mind"))
        workerRepository.create(Worker("Guillaume", "EHRET", company))
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(DevmindKotlinApplication::class.java, *args)
}

