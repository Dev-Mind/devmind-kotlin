package com.devmind.web

import com.devmind.model.Worker
import com.devmind.repository.WorkerRepository
import org.springframework.web.bind.annotation.*

/**
 * @author Dev-Mind <guillaume@dev-mind.fr>
 * @since 22/09/16.
 */
@RestController
@RequestMapping("/workers")
class WorkerController(val workerRepository: WorkerRepository){

    @GetMapping
    fun list() = workerRepository.findAll();

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int) = workerRepository.findById(id);

    @PostMapping
    fun create(@RequestBody worker: Worker) = workerRepository.create(worker)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody worker: Worker) = workerRepository.update(id, worker);


}