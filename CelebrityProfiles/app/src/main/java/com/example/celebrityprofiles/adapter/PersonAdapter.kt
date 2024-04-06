package com.example.celebrityprofiles.adapter

import android.provider.Settings.System.getString
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.celebrityprofiles.R
import com.example.celebrityprofiles.databinding.ListItemPersonBinding
import com.example.celebrityprofiles.model.Person
import com.example.celebrityprofiles.model.PersonDiffUtillCallback

class PersonAdapter(
    private val onPersonClick: (Person) -> Unit
) : ListAdapter<Person, PersonAdapter.ViewHolder>(PersonDiffUtillCallback()) {

    /**
     * метод который будет создавать view для каждого объекта
     * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        return ViewHolder(
            ListItemPersonBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    /**
     * для вызова метода из ViewHolder`s
     * */

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ListItemPersonBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(person:Person){
            with(binding){
                personName.text = person.name.split(" ").map { it.capitalize() }.joinToString(" ")
                personAge.text = root.context.getString(R.string.age_format, person.age?.toString()?:"unknown")
                personNation.text = root.context.getString(R.string.nation_format, person.nationality?:"unknown")
                personBirthday.text = root.context.getString(R.string.birthday_format, person.birthday?:"unknown")
                personNetWorth.text = root.context.getString(R.string.net_worth_format, person.netWorth.toString())
                personOccupation.text = root.context.getString(R.string.occupation_format, person.occupation?.get(0) ?:"unknown")
                personHeight.text = root.context.getString(R.string.height_format, person.height.toString())
                personGender.text = root.context.getString(R.string.gender_format, person.gender)

                root.setOnClickListener(){
                    onPersonClick(person)
                }
            }
        }
    }

}


