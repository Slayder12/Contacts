package com.example.contacts.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.contacts.repository.ContactRepository
import com.example.contacts.utils.ContactsDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactViewModel(application: Application): AndroidViewModel(application) {

    private val repository: ContactRepository
    val personList: LiveData<List<Person>>

    init {
        val dao = ContactsDataBase.getDatabase(application).getPersonDao()
        repository = ContactRepository(dao)
        personList = repository.personList
    }

    fun deletePerson(person: Person) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(person)
    }

    fun insertPerson(person: Person) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(person)
    }
}
