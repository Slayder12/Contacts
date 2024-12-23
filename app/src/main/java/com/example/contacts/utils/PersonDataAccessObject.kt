package com.example.contacts.utils

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.contacts.models.Person

@Dao
interface PersonDataAccessObject {
    @Insert
    suspend fun insert(person: Person)

    @Delete
    suspend fun delete(person: Person)

    @Update
    suspend fun update(person: Person)

    @Query("SELECT * FROM persons_table ORDER BY id ASC")
    fun getAllPersons() : LiveData<List<Person>>

    @Query("DELETE FROM persons_table")
    fun deleteAll()
}