package com.thepyprogrammer.gaitanalyzer.ui.main.freeze.freezeData

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.thepyprogrammer.gaitanalyzer.R

class FreezeDataAdapter(
    private val activity: AppCompatActivity,
    private val freezes: MutableList<Pair<String, Int>> = mutableListOf()
) :
    RecyclerView.Adapter<FreezeDataAdapter.FreezeDataViewHolder>() {
    private val completeViewModel: CompleteViewModel =
        ViewModelProvider((activity as FragmentActivity)).get(CompleteViewModel::class.java)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FreezeDataViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_freeze, parent, false)
        return FreezeDataViewHolder(v)
    }

    override fun onBindViewHolder(holder: FreezeDataViewHolder, position: Int) {
        val item = freezes[position]
        val date = item.first
        val freeze = item.second
        holder.apply {
            itemDate.text = date
            itemFreeze.text = freeze.toString()
            itemFreeze.setTextColor(
                ContextCompat.getColor(
                    activity,
                    when {
                        freeze >= 30 ->
                            R.color.fire_brick
                        freeze >= 10 ->
                            R.color.SchoolBusYellow
                        else ->
                            R.color.green
                    }
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return freezes.size
    }

    inner class FreezeDataViewHolder(itemView: View) : FreezeViewHolder(itemView, completeViewModel)

}