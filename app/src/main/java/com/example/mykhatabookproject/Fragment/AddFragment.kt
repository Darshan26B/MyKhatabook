package com.example.mykhatabookproject.Fragment

import android.annotation.SuppressLint
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

    @SuppressLint("ResourceType")
    private fun initView() {

        binding.CardIncome.setOnClickListener {
            isExpense=0
            binding.CardIncome.setCardBackgroundColor(Color.parseColor("#05BFDB"))
            binding.CardExpense.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
            binding.Income.setTextColor(Color.parseColor("#FFFFFF"))
            binding.Expense.setTextColor(Color.parseColor("#000000"))
        }
        binding.CardExpense.setOnClickListener {
            isExpense=1
            binding.CardIncome.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
            binding.CardExpense.setCardBackgroundColor(Color.parseColor("#05BFDB"))
            binding.Expense.setTextColor(Color.parseColor("#FFFFFF"))
            binding.Income.setTextColor(Color.parseColor("#000000"))
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
        var date= Date()
        var Format= SimpleDateFormat("dd/MM/YYYY")
        var currentDate=Format.format(date)

        var dates=currentDate.split("/")
        binding.txtDateAdd.text=currentDate
        binding.txtDateAdd.setOnClickListener {


            var  dialog = DatePickerDialog(requireContext(), object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {

                    var mo = "0"+(month+1)
                    var selecteDate ="$day/$mo/$year"
                    binding.txtDateAdd.text=selecteDate

                }

            },dates[2].toInt(),dates[1].toInt()-1,dates[0].toInt())
            dialog.show()
        }

    }

}