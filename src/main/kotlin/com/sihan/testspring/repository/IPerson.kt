package com.sihan.testspring.repository

import com.sihan.testspring.models.Person
import java.util.UUID

interface IPerson {
    fun getAllPerson(): List<Person>
    fun getPersonById(id: UUID): Person
    fun addPerson(person: Person): UUID
    fun deletePerson(id: UUID): Boolean
    fun updatePerson(person: Person): Boolean
}