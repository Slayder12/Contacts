package com.example.contacts.utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.contacts.models.Person

@Database(entities = [Person::class], version = 1, exportSchema = false)
abstract class ContactsDataBase : RoomDatabase() {
    abstract fun getPersonDao() : PersonDataAccessObject
    companion object {
        private var INSTANCE: ContactsDataBase? = null
        fun getDatabase(context: Context) : ContactsDataBase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactsDataBase::class.java,
                    "contacts_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}