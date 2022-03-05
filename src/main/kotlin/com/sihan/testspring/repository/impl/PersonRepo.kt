package com.sihan.testspring.repository.impl

import com.sihan.testspring.models.Person
import com.sihan.testspring.repository.IPerson
import org.springframework.stereotype.Repository
import java.util.*

@Repository("MemoryPerson")
class PersonRepo : IPerson {
    private val persons = mutableListOf<Person>()

    override fun getAllPerson(): List<Person> {
        return persons
    }

    override fun getPersonById(id: UUID): Person {
        return persons.first { p -> p.id == id }
    }

    override fun addPerson(person: Person): UUID {
        persons.add(person)
        return person.id
    }

    override fun deletePerson(id: UUID): Boolean {
        return persons.removeIf { p -> p.id == id }
    }

    override fun updatePerson(person: Person): Boolean {
        persons.first { p -> p.id == person.id }.let {
            it.name = person.name
            true
        }
        return false
    }
}