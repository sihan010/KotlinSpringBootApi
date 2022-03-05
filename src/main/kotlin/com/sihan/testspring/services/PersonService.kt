package com.sihan.testspring.services

import com.sihan.testspring.dto.PersonDTO
import com.sihan.testspring.models.Person
import com.sihan.testspring.repository.impl.PersonRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonService(
    @Autowired @Qualifier("MemoryPerson") private val personRepo: PersonRepo
) {
    fun getAllPerson(): List<Person> {
        return personRepo.getAllPerson()
    }

    fun getPersonById(id: UUID): Person {
        return personRepo.getPersonById(id)
    }

    fun addPerson(person: PersonDTO): UUID {
        val uuid = UUID.randomUUID()
        personRepo.addPerson(
            Person(
                uuid,
                person.name
            )
        )
        return uuid
    }

    fun deletePerson(id: UUID): Boolean {
        return personRepo.deletePerson(id)
    }

    fun updatePerson(id: UUID, person: PersonDTO): Boolean {
        return personRepo.updatePerson(
            Person(id, person.name)
        )
    }
}