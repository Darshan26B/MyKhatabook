package com.example.mykhatabookproject

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mykhatabookproject.databinding.TransItemBinding
import kotlin.collections.ArrayList

class TransListAdapter(update:(TransModel)->Unit,delete:(Int)->Unit): RecyclerView.Adapter<TransListAdapter.TransListHolder>() {

    var update = update
    var transList = ArrayList<TransModel>()
    var delete=delete
    lateinit var Context: Context


    class TransListHolder(itemView: TransItemBinding) : ViewHolder(itemView.root) {
        var binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransListHolder {
        Context = parent.context
        var binding = TransItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransListHolder(binding)
    }

    override fun getItemCount(): Int {
        return transList.size
    }

    override fun onBindViewHolder(
        holder: TransListHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        holder.binding.apply {
            transList.get(position).apply {
                txtCategory.text = Category
                txtNote.text = Note
                txtAmount.text = Amt.toString()


                if (IsExpense == 0) {
                    txtAmount.setTextColor(Color.GREEN)
                    roundArrow.setImageResource(R.drawable.call_made)
                    round.setImageResource(R.drawable.inc_exp_bg)
                } else {
                    txtAmount.setTextColor(Color.RED)
                    roundArrow.setImageResource(R.drawable.baseline_call_received_24)
                    round.setImageResource(R.drawable.red)
                }


            }
            holder.itemView.setOnLongClickListener(object : OnLongClickListener {
                override fun onLongClick(v: View?): Boolean {

                    var PopupMenu = PopupMenu(Context, holder.itemView)
                    PopupMenu.menuInflater.inflate(R.menu.popomenu, PopupMenu.menu)

                    PopupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
                        override fun onMenuItemClick(item: MenuItem?): Boolean {

                            if (item?.itemId == R.id.Edit) {
                                update.invoke(transList.get(position))
                            }
                            if (item?.itemId == R.id.Delete) {
                                delete.invoke(transList.get(position).id)
                            }


                            return true
                        }

                    })
                    PopupMenu.show()

                    return false
                }

            })
        }
    }

    fun setTrans(transList: ArrayList<TransModel>) {
        this.transList = transList
    }

    fun updateData(trans: java.util.ArrayList<TransModel>) {
        transList = trans
        notifyDataSetChanged()
    }

}