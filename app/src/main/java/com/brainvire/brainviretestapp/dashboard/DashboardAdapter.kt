package com.brainvire.brainviretestapp.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.brainvire.brainviretestapp.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.layout_datalist_adapter.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class DashboardAdapter(var context: Context, var mainDataList: HashMap<String, Any>) :
    RecyclerView.Adapter<DashboardAdapter.CustomViewHolder>() {

    val dateList: MutableSet<String> = mainDataList.keys
    var subList: MutableCollection<Any> = mainDataList.values

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val adapterView =
            LayoutInflater.from(context).inflate(R.layout.layout_datalist_adapter, parent, false)
        return CustomViewHolder(adapterView)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        try {
            holder.date.text = getFormattedDate(dateList.elementAt(position),"dd/mm/yyyy","yyyy-mm-dd")
            holder.rvVaseDataGrid.layoutManager = GridLayoutManager(context, 3)
            val subDataMap: HashMap<String, Double> = Gson().fromJson(
                subList.elementAt(position).toString(), HashMap::class.java
            ) as HashMap<String, Double>
            holder.rvVaseDataGrid.adapter = SubListAdapter(context, subDataMap)
        }
        catch (e: Exception)
        {
         e.printStackTrace()
        }

    }

    override fun getItemCount(): Int {
        return mainDataList.size
    }

    fun getFormattedDate(date: String, newFormat: String, existingFormat: String): String {
        try {
            return SimpleDateFormat(newFormat, Locale.getDefault()).format(
                SimpleDateFormat(existingFormat, Locale.getDefault()).parse(date)
            )
        } catch (pe: ParseException) {
           pe.printStackTrace()
        }
        return ""
    }

    //viewholder class
    class CustomViewHolder(listItem: View) : RecyclerView.ViewHolder(listItem) {
        var date: AppCompatTextView = listItem.tvDateData
        var rvVaseDataGrid: RecyclerView = listItem.rvBaseDataGrid
    }
}