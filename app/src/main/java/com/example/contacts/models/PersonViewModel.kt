package com.example.contacts.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.contacts.PersonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonViewModel(application: Application): AndroidViewModel(application) {

    private val repository: PersonRepository
    val personList: LiveData<List<Person>>

    init {
        val dao = ContactsDataBase.getDatabase(application).getPersonDao()
        repository = PersonRepository(dao)
        personList = repository.personList
    }

    fun deletePerson(person: Person) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(person)
    }

    fun insertPerson(person: Person) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(person)
    }
}
