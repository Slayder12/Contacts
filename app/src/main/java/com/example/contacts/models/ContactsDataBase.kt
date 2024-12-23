package com.example.contacts.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

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