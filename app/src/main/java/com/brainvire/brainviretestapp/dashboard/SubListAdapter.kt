package com.brainvire.brainviretestapp.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.brainvire.brainviretestapp.R
import kotlinx.android.synthetic.main.layout_sublist_adapter.view.*

class SubListAdapter(var context:Context, var subDataList:HashMap<String,Double>): RecyclerView.Adapter<SubListAdapter.SubListViewHolder>() {

    var dataNameList:MutableSet<String> = subDataList.keys
    var dataValueList: MutableCollection<Double> = subDataList.values

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubListViewHolder {
        val adapterView = LayoutInflater.from(context).inflate(R.layout.layout_sublist_adapter, parent,false)
        return SubListViewHolder(adapterView)
    }

    override fun onBindViewHolder(holder: SubListViewHolder, position: Int) {
        holder.tvDataName.text = dataNameList.elementAt(position)
        holder.tvDataValue.text = dataValueList.elementAt(position).toString()
    }

    override fun getItemCount(): Int {
        return subDataList.size
    }

    class SubListViewHolder(listItem:View):RecyclerView.ViewHolder(listItem)
    {
      var tvDataName :AppCompatTextView = listItem.tvDataName
      var tvDataValue :AppCompatTextView = listItem.tvDataValue
    }
}