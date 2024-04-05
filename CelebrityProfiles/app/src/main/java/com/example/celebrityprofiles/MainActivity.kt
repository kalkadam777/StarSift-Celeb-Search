package com.example.celebrityprofiles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.celebrityprofiles.databinding.ActivityMainBinding
import com.example.celebrityprofiles.fragment.PersonListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val personListFragment = PersonListFragment.newInstance()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, personListFragment)
            .commit()
    }
}