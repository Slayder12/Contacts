package com.example.contacts

import androidx.lifecycle.LiveData
import com.example.contacts.models.Person
import com.example.contacts.models.PersonDataAccessObject

class PersonRepository(private val personDao: PersonDataAccessObject) {
    val personList: LiveData<List<Person>> = personDao.getAllPersons()

    suspend fun insert(person: Person){
        personDao.insert(person)
    }

    suspend fun delete(person: Person){
        personDao.delete(person)
    }
}