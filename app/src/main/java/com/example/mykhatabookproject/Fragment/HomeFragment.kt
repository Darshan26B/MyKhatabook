package com.example.mykhatabookproject.Fragment

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mykhatabookproject.DBHelper
import com.example.mykhatabookproject.TransListAdapter
import com.example.mykhatabookproject.TransModel
import com.example.mykhatabookproject.databinding.FragmentHomeBinding
import com.example.mykhatabookproject.databinding.UpdateLayoutBinding
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

        var date=Date()
        var Format=SimpleDateFormat("dd/MM/YYYY")
        var currentDate=Format.format(date)

        var dates=currentDate.split("/")
        binding.txtDate.text=currentDate
        binding.txtDate.setOnClickListener {


            var  dialog = DatePickerDialog(requireContext(), object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {

                    var mo = "0"+(month+1)
                    var selecteDate ="$day/$mo/$year"
                    binding.txtDate.text=selecteDate

                }

            },dates[2].toInt(),dates[1].toInt()-1,dates[0].toInt())
            dialog.show()
        }
        dbhelper= DBHelper(context)
        transList=dbhelper.getTrans()
        UpdateTotal(transList)


        adapter= TransListAdapter({

            Update(it)
        },{
            deleteTrans(it)
        })
        adapter.setTrans(transList)

        binding.rcvTransList.layoutManager=LinearLayoutManager(context)
        binding.rcvTransList.adapter=adapter


        return binding.root
    }

    private fun deleteTrans(it: Int) {
        var dialog = AlertDialog.Builder(requireContext())
            .setTitle("Delete Transaction")
            .setMessage("Are you want sure?")
            .setPositiveButton("yes", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    dbhelper.deleteTrans(it)
                    adapter.updateData(dbhelper.getTrans())
                    UpdateTotal(dbhelper.getTrans())
                }

            }).setNegativeButton("No", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {

                }
            }).create()
        dialog.show()
    }
    fun UpdateTotal(transList: ArrayList<TransModel>) {
        var totalIncome=0
        var totalExpense = 0
        for (trans in transList) {
            if (trans.IsExpense==0) {
                totalIncome +=trans.Amt
            }else {
                totalExpense += trans.Amt
            }
        }
            binding.txtIncome.text = totalIncome.toString()
            binding.txtExpense.text=totalExpense.toString()
            binding.allover.text = (totalIncome-totalExpense).toString()

    }

  private  fun Update(transList: TransModel) {
        var dialog = Dialog(requireContext())
        var bind = UpdateLayoutBinding.inflate(layoutInflater)
        dialog.setContentView(bind.root)

        bind.edtAmount.setText(transList.Amt.toString())
        bind.edtCategory.setText(transList.Category.toString())
        bind.edtNote.setText(transList.Note.toString())


        bind.btnSave.setOnClickListener {
            var amount=bind.edtAmount.text.toString().toInt()
            var category=bind.edtCategory.text.toString()
            var note=bind.edtNote.text.toString()
            var isExpense = transList.IsExpense
            var model=TransModel(transList.id,amount,category,note,isExpense)
            dbhelper.updateTrans(model)
            adapter.updateData(dbhelper.getTrans())
            UpdateTotal(dbhelper.getTrans())
            dialog.dismiss()
        }

        dialog.show()
    }


}