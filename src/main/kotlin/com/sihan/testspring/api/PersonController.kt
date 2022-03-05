package com.sihan.testspring.api

import com.sihan.testspring.common.ResponseExtensions
import com.sihan.testspring.dto.PersonDTO
import com.sihan.testspring.models.Person
import com.sihan.testspring.services.PersonService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/api/v1/person")
@RestController
class PersonController(
    @Autowired private val personService: PersonService
) {
    private val logger = LoggerFactory.getLogger(PersonController::class.java)

    @GetMapping
    fun getAllPerson(): ResponseEntity<List<Person>> {
        return ResponseEntity(
            personService.getAllPerson(),
            ResponseExtensions.commonHeaders(),
            HttpStatus.OK
        )
    }

    @GetMapping(path = ["{id}"])
    fun getPersonById(@PathVariable("id") id: UUID): ResponseEntity<Person> {
        return try {
            ResponseEntity(
                personService.getPersonById(id),
                ResponseExtensions.commonHeaders(),
                HttpStatus.OK
            )
        } catch (ex: Exception) {
            ResponseEntity(
                ResponseExtensions.commonHeaders(),
                HttpStatus.NOT_FOUND
            )
        }
    }

    @PostMapping
    fun addPerson(@RequestBody person: PersonDTO): ResponseEntity<Unit> {
        val uuid = personService.addPerson(person)
        return ResponseEntity(
            ResponseExtensions.createdHeaders("api/v1/person/${uuid}"),
            HttpStatus.CREATED
        )
    }

    @DeleteMapping(path = ["{id}"])
    fun deletePerson(@PathVariable("id") id: UUID): ResponseEntity<Unit> {
        val removed = personService.deletePerson(id)
        return ResponseEntity(
            ResponseExtensions.commonHeaders(),
            if (removed) HttpStatus.OK else HttpStatus.NOT_FOUND
        )
    }

    @PatchMapping(path = ["{id}"])
    fun updatePerson(@PathVariable("id") id: UUID, @RequestBody person: PersonDTO): ResponseEntity<Unit> {
        return try {
            val updated = personService.updatePerson(id, person)
            return ResponseEntity(
                ResponseExtensions.commonHeaders(),
                if (updated) HttpStatus.OK else HttpStatus.NOT_FOUND
            )
        } catch (ex: Exception) {
            ResponseEntity(
                ResponseExtensions.commonHeaders(),
                HttpStatus.NOT_FOUND
            )
        }
    }
}