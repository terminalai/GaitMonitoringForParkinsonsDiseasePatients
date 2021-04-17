package com.thepyprogrammer.gaitanalyzer.ui.main.freeze.freezeData

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.thepyprogrammer.gaitanalyzer.R

class FreezeDataAdapter(private val context: Context) :
        RecyclerView.Adapter<FreezeDataAdapter.ViewHolder>() {
    private val completeViewModel: CompleteViewModel = ViewModelProvider((context as FragmentActivity)).get(CompleteViewModel::class.java)
    private val titles = arrayOf<String>()

    private val details = arrayOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemDetails.text = details[position]
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemTitle: TextView = itemView.findViewById(R.id.item_title)
        var itemDetails: TextView = itemView.findViewById(R.id.item_detail)
        var context: Context = itemView.context

        init {
            itemView.setOnClickListener {
                val navController = Navigation.findNavController(it)
                val position = adapterPosition
                completeViewModel.adapterPosition.value = position
                navController.navigate(R.id.action_freezeFragment_to_selectedFragment)
            }
        }
    }

}