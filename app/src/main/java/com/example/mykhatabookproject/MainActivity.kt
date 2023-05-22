package com.example.mykhatabookproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mykhatabookproject.Fragment.AddFragment
import com.example.mykhatabookproject.Fragment.HomeFragment
import com.example.mykhatabookproject.Fragment.StatsFragment
import com.example.mykhatabookproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(HomeFragment())
        binding.BNV.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.Home ->loadFragment(HomeFragment())
                R.id.add ->loadFragment(AddFragment())
                R.id.Stats ->loadFragment(StatsFragment())

                else->{

                }
            }
            true
        }

    }

    private fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragfrem,fragment).commit()
    }
}