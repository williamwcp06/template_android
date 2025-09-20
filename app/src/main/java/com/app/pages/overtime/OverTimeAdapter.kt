package com.app.pages.menu.overtime

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class OverTimeAdapter(private val adapterOnClick: () -> Unit) : RecyclerView.Adapter<OverTimeAdapter.OvertimaHolder>() {

    private var data: ArrayList<OverTimeModel> = arrayListOf()

    var completionLongPress: (Int) -> Unit = {}

    override fun getItemCount(): Int = data.count()

    override fun onCreateViewHolder(viewGroup: ViewGroup, index: Int): OvertimaHolder {
        return OvertimaHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.overtime_adapter, viewGroup, false))
    }

    override fun onBindViewHolder(holder: OvertimaHolder, position: Int) {
        holder.bindData(position)
    }

    inner class OvertimaHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(index: Int) {
            val _data = data[index]
            val tvDate = itemView.findViewById<TextView>(R.id.tvDate)
            tvDate.text = "${_data.tgl} - ${_data.durasi_jam}jam"
            val tvStatus = itemView.findViewById<TextView>(R.id.tvStatus)
            tvStatus.text = _data.status?.name?.uppercase() ?: InboxStatus.pending.name.uppercase()
            tvStatus.setTextColor(_data.status?.getColor() ?: InboxStatus.pending.getColor())
        }
    }

    fun setData(data: ArrayList<OverTimeModel>) {
        this.data.clear()
        this.data.addAll(data)
        GlobalScope.async(Dispatchers.Main) {
            notifyDataSetChanged()
        }
    }

}
