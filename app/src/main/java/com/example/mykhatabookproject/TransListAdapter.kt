package com.example.mykhatabookproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mykhatabookproject.databinding.TransItemBinding
import kotlin.collections.ArrayList

class TransListAdapter: RecyclerView.Adapter<TransListAdapter.TransListHolder>() {
    var transList= ArrayList<TransModel>()
    class TransListHolder(itemView: TransItemBinding) : ViewHolder(itemView.root) {
        var binding=itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransListHolder {
        var binding=TransItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TransListHolder(binding)
    }

    override fun getItemCount(): Int {
        return transList.size
    }

    override fun onBindViewHolder(holder: TransListHolder, position: Int) {
        holder.binding.apply {
            transList.get(position).apply {
            txtCategory.text=Category
                txtNote.text=Note
                txtCategory.text=Amt.toString()

            }
        }
    }

    fun setTrans(transList: ArrayList<TransModel>) {
        this.transList=transList
    }

}