package com.app.common.adapter

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class BasicListAdapter(private val adapterOnClick: (Int) -> Unit) : RecyclerView.Adapter<BasicListAdapter.BasicHolder>() {

    private var data: MutableList<String> = mutableListOf()
    private var header: String? = null

    var completionLongPress: (Int) -> Unit = {}

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): BasicHolder {
        return BasicHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.basic_list, viewGroup, false))
    }

    override fun getItemCount(): Int = data.size + if (header == null) 0 else 1

    override fun onBindViewHolder(holder: BasicHolder, position: Int) {
        if (header == null) {
            holder.bindData(data, position)
        } else {
            holder.bindData(if(position == 0) null else data, position)
        }
    }

    inner class BasicHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(data: List<String>?, position: Int) {
            val tvBasic = itemView.findViewById<TextView>(R.id.tv_basic)
            val _position = position - if (header == null) 0 else 1
            tvBasic.text = if (data == null) header ?: "" else data[_position]
            setAction(tvBasic, data?.get(_position), position)
        }

        fun setAction(textView: TextView, data: String?, position: Int) {
            textView.setTypeface(if (data == null) Typeface.DEFAULT_BOLD else Typeface.DEFAULT)
            if (header != null && position == 0) {} else {
//                data?.let { data ->
                    textView.setOnLongClickListener {
                        completionLongPress(if (header == null) position else position-1)
                        true
                    }
                    textView.setOnClickListener{
                        adapterOnClick(if (header == null) position else position-1)
                    }
//                }
            }
        }
    }

    fun setData(data: List<String>, header: String? = null, clearData: Boolean = false) {
        if (clearData) {
            this.data.clear()
        }
        this.data.addAll(data)
        this.header = header

        GlobalScope.async(Dispatchers.Main) {
            notifyDataSetChanged()
        }
    }

    fun addData(data: String) {
        this.data.add(data)
        GlobalScope.async(Dispatchers.Main) {
            notifyDataSetChanged()
        }
    }
}
