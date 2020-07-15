package com.example.studentrecords.adapters

import android.graphics.Color.alpha
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListener
import androidx.recyclerview.widget.RecyclerView
import com.example.studentrecords.R
import com.example.studentrecords.models.Student
import jp.wasabeef.recyclerview.animators.holder.AnimateViewHolder

class AdapterRv(val dataList: ArrayList<Student>, private val itemClicked: ItemClicked) :
    RecyclerView.Adapter<AdapterRv.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = dataList[position].name
        holder.tvStudentClass.text = """Class :${dataList[position].studentClass}"""
        holder.parent.setOnClickListener(View.OnClickListener {
            itemClicked.onItemClicked(holder.adapterPosition)
        })
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val tvName = itemView.findViewById<TextView>(R.id.tvName)
        val tvStudentClass = itemView.findViewById<TextView>(R.id.tvStudentClass)
        val parent = itemView.findViewById<ConstraintLayout>(R.id.parent)
    }

    interface ItemClicked {
        fun onItemClicked(position: Int)
    }
}