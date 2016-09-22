package com.devmind.web

import com.devmind.model.Company
import com.devmind.repository.CompanyRepository
import org.springframework.web.bind.annotation.*

/**
 * @author Dev-Mind <guillaume@dev-mind.fr>
 * @since 22/09/16.
 */
@RestController
@RequestMapping("/companies")
class CompanyController(val companyRepository: CompanyRepository){

    @GetMapping
    fun list() = companyRepository.findAll();

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int) = companyRepository.findById(id);
    
    @PostMapping
    fun create(@RequestBody company: Company) = companyRepository.create(company)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody company: Company) = companyRepository.update(id, company);


}