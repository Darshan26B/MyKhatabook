package com.example.mykhatabookproject.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mykhatabookproject.R
import com.example.mykhatabookproject.databinding.FragmentStatsBinding


class StatsFragment : Fragment() {
  lateinit var binding: FragmentStatsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     binding=FragmentStatsBinding.inflate(layoutInflater)




        return binding.root
    }


}