package com.example.gasmonitorapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_recentdataitems.view.*


class COReadingAdapter(private val context: ViewRecentCOReadingsActivity, private val readingList: MutableList<COReading>) :
    RecyclerView.Adapter<COReadingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_recentdataitems, parent, false))
    }
    override fun getItemCount(): Int {
        return readingList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.COLevel.text = readingList.get(position).reading
        holder.timeRecorded.text = readingList.get(position).date.toString()
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val COLevel = view.txtCO2Level
        val timeRecorded = view.txtDateTimeRecorded
    }
}