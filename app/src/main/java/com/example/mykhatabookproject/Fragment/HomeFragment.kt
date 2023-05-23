package com.example.mykhatabookproject.Fragment

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mykhatabookproject.DBHelper
import com.example.mykhatabookproject.R
import com.example.mykhatabookproject.TransListAdapter
import com.example.mykhatabookproject.TransModel
import com.example.mykhatabookproject.databinding.FragmentAddBinding
import com.example.mykhatabookproject.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.Date


class HomeFragment : Fragment() {

    var transList=ArrayList<TransModel>()
    lateinit var dbhelper:DBHelper
    lateinit var binding: FragmentHomeBinding
    lateinit var adapter:TransListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        try {

        }catch (e:Exception) {

        }
        binding = FragmentHomeBinding.inflate(layoutInflater)


        binding.txtDate.setOnClickListener {
            var date=Date()
            var Format=SimpleDateFormat("DD/MM/YYYY")
            var currentDate=Format.format(date)

            var dates=currentDate.split("/")
            binding.txtDate.text=currentDate

            var  dialog = DatePickerDialog(requireContext(), object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

                    var selecteDate ="$dayOfMonth/${(month+1)}/$year"
                    binding.txtDate.text=selecteDate

                }

            },dates[2].toInt(),dates[1].toInt()-1,dates[0].toInt())
            dialog.show()
        }
        dbhelper= DBHelper(context)
        transList=dbhelper.getTrans()
        adapter= TransListAdapter()
        adapter.setTrans(transList)

        binding.rcvTransList.layoutManager=LinearLayoutManager(context)
        binding.rcvTransList.adapter=adapter


        return binding.root
    }


}