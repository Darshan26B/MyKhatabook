package com.example.mykhatabookproject.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mykhatabookproject.DBHelper
import com.example.mykhatabookproject.R
import com.example.mykhatabookproject.TransListAdapter
import com.example.mykhatabookproject.TransModel
import com.example.mykhatabookproject.databinding.FragmentAddBinding
import com.example.mykhatabookproject.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    var transList=ArrayList<TransModel>()
    lateinit var dbhelper:DBHelper
    lateinit var binding: FragmentHomeBinding
    lateinit var adapter:TransListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        dbhelper= DBHelper(context)
        transList=dbhelper.getTrans()
        adapter= TransListAdapter()
        adapter.setTrans(transList)

        binding.rcvTransList.layoutManager=LinearLayoutManager(context)
        binding.rcvTransList.adapter=adapter


        return binding.root
    }


}