package com.example.contacts.models

import android.content.Context
import android.widget.Toast
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persons_table")
data class Person (
    @ColumnInfo(name = "firstName") var firstName: String,
    @ColumnInfo(name = "lastName") var lastName: String,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "phoneNumber") var phoneNumber: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    companion object{
        fun isValidate(context: Context, person: Person): Boolean {
            if (person.firstName.isEmpty() && person.lastName.isEmpty()  && person.phoneNumber.isEmpty()) {
                Toast.makeText(context,  "Введите данные", Toast.LENGTH_SHORT).show()
                return false
            }
            if (person.firstName.isEmpty()) {
                Toast.makeText(context, "Введите имя", Toast.LENGTH_SHORT).show()
                return false
            }
            if (person.firstName.length !in 2..32) {
                Toast.makeText(context, "Введите корректное имя", Toast.LENGTH_SHORT).show()
                return false
            }
            if (person.lastName.isEmpty()) {
                Toast.makeText(context,
                    "Введите фамилию", Toast.LENGTH_SHORT).show()
                return false
            }
            if (person.lastName.length !in 2..32) {
                Toast.makeText(context, "Введите корректную фамилию", Toast.LENGTH_SHORT).show()
                return false
            }

            if (person.phoneNumber.isEmpty()) {
                Toast.makeText(context,
                    "Введите номер телефона", Toast.LENGTH_SHORT).show()
                return false
            }
            if (person.phoneNumber.length !in 10..15) {
                Toast.makeText(context,
                    "Введите корректный номер", Toast.LENGTH_SHORT).show()
                return false
            }
            return true
        }
    }

}


