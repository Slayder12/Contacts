package com.example.contacts

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.models.Person

class PersonAdapter(private val context: Context, private val listener: PersonClickListener): RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    private val personList = ArrayList<Person>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Person>){
        personList.clear()
        personList.addAll(newList)
        notifyDataSetChanged()
    }

    inner class PersonViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
            private val itemFirstNameTV: TextView = itemView.findViewById(R.id.itemFirstNameTV)
            private val itemLastNameTV: TextView = itemView.findViewById(R.id.itemLastNameTV)
            private val itemNumberTV: TextView = itemView.findViewById(R.id.itemNumberTV)
            val itemIconDeleteIV: ImageView = itemView.findViewById(R.id.itemIconDeleteIV)

            fun bind(person: Person){
                itemFirstNameTV.text = person.firstName
                itemLastNameTV.text = person.lastName
                itemNumberTV.text = person.phoneNumber
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val viewHolder
            = PersonViewHolder(LayoutInflater.from(context).inflate(R.layout.person_list, parent,false))
        viewHolder.itemIconDeleteIV.setOnClickListener{
            listener.onItemClicked(personList[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount() = personList.size

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val currentPerson = personList[position]
        holder.bind(currentPerson)
    }

    interface PersonClickListener{
        fun onItemClicked(person: Person)
    }
}