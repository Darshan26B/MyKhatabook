package com.example.mykhatabookproject.Fragment

import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.example.mykhatabookproject.DBHelper
import com.example.mykhatabookproject.R
import com.example.mykhatabookproject.TransModel
import com.example.mykhatabookproject.databinding.FragmentAddBinding
import java.text.SimpleDateFormat
import java.util.Date


class AddFragment : Fragment() {

    lateinit var binding: FragmentAddBinding
    var isExpense = 0
    lateinit var dbHelper: DBHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentAddBinding.inflate(layoutInflater)


        dbHelper= DBHelper(context)

        initView()

        return binding.root
    }

    private fun initView() {

        binding.CardVIncome.setOnClickListener {
            isExpense=0
            binding.CardVIncome.setCardBackgroundColor(Color.parseColor("#FF9E80"))
            binding.CardVExpense.setCardBackgroundColor(Color.parseColor("#FFFFF"))
        }
        binding.CardVExpense.setOnClickListener {
            isExpense=1
            binding.CardVIncome.setCardBackgroundColor(Color.parseColor("#FF9E80"))
            binding.CardVExpense.setCardBackgroundColor(Color.parseColor("#FFFFF"))
        }


        binding.btnSave.setOnClickListener {
            var amount=binding.edtAmount.text.toString().toInt()
            var category=binding.edtCategory.text.toString()
            var note=binding.edtNote.text.toString()
            var model=TransModel(1,amount,category,note,isExpense)
            dbHelper.addTrans(model)
            binding.edtAmount.setText("")
            binding.edtCategory.setText("")
            binding.edtNote.setText("")
        }
        binding.txtDateAdd.setOnClickListener {
            var date= Date()
            var Format= SimpleDateFormat("DD/MM/YYYY")
            var currentDate=Format.format(date)

            var dates=currentDate.split("/")
            binding.txtDateAdd.text=currentDate

            var  dialog = DatePickerDialog(requireContext(), object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

                    var selecteDate ="$dayOfMonth/${(month+1)}/$year"
                    binding.txtDateAdd.text=selecteDate

                }

            },dates[2].toInt(),dates[1].toInt()-1,dates[0].toInt())
            dialog.show()
        }

    }

}