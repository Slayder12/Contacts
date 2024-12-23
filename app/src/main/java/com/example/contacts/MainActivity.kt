package com.example.contacts

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.models.Person
import com.example.contacts.models.PersonViewModel



class MainActivity : AppCompatActivity(), PersonAdapter.PersonClickListener {

    private lateinit var personViewModel: PersonViewModel

    private lateinit var toolbar: Toolbar
    private lateinit var firstNameET: EditText
    private lateinit var lastNameET: EditText
    private lateinit var phoneNumberET: EditText
    private lateinit var saveBTN: Button
    private lateinit var recyclerViewRV: RecyclerView

    @SuppressLint("MissingInflatesId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        recyclerViewRV.layoutManager = LinearLayoutManager(this)
        val adapter = PersonAdapter(this, this)
        recyclerViewRV.adapter = adapter

        personViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
            .getInstance(application)
        )[PersonViewModel::class.java]
        personViewModel.personList.observe(this) { list ->
            list?.let {
                adapter.updateList(it)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        saveBTN.setOnClickListener{
            val firstName = firstNameET.text.toString()
            val lastName = lastNameET.text.toString()
            val phoneNumber = phoneNumberET.text.toString()
            val person = Person(firstName, lastName, phoneNumber)

            if(Person.isValidate(this, person)){
                personViewModel.insertPerson(person)
                Toast.makeText(this, "${person.firstName} Добавлен", Toast.LENGTH_SHORT).show()
            }else{
                return@setOnClickListener
            }
            clearEditText()
        }

    }

    private fun clearEditText() {
        firstNameET.text.clear()
        lastNameET.text.clear()
        phoneNumberET.text.clear()
    }

    private fun init() {

        toolbar = findViewById(R.id.toolbarMain)
        title = ""
        setSupportActionBar(toolbar)

        firstNameET = findViewById(R.id.firstNameET)
        lastNameET = findViewById(R.id.lastNameET)
        phoneNumberET = findViewById(R.id.phoneNumberET)

        saveBTN = findViewById(R.id.saveBTN)
        recyclerViewRV = findViewById(R.id.recyclerViewRV)

        personViewModel = ViewModelProvider(this)[PersonViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.exitMenu) {
            Toast.makeText(this, "Программа завершена", Toast.LENGTH_SHORT).show()
            finishAffinity()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClicked(person: Person) {
        personViewModel.deletePerson(person)
        Toast.makeText(this, "${person.firstName} удалён", Toast.LENGTH_SHORT).show()
    }
    fun saveData(view: View){
        val firstName = firstNameET.text.toString()
        val lastName = lastNameET.text.toString()
        val phoneNumber = phoneNumberET.text.toString()
        val person = Person(firstName, lastName, phoneNumber)

        if(Person.isValidate(this, person)){
            personViewModel.insertPerson(person)
            Toast.makeText(this, "${person.firstName} Добавлен", Toast.LENGTH_SHORT).show()
        }
        clearEditText()

    }
}