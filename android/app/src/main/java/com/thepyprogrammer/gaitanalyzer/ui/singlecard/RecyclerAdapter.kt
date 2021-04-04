package com.thepyprogrammer.gaitanalyzer.ui.singlecard

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

class RecyclerAdapter(private val context: Context) :
        RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private val completeViewModel: CompleteViewModel
    private val titles = """Opening and Closing Ceremonies
3x3 Basketball
Archery
Artistic Gymnastics
Artistic Swimming
Athletics
Badminton
Baseball/Softball
Basketball
Beach Volleyball
Boxing
Canoe Slalom
Canoe Sprint
Cycling BMX Freestyle
Cycling BMX Racing
Cycling Mountain Bike
Cycling Road
Cycling Track
Diving
Equestrian
Fencing""".split("\n").toTypedArray()

    private val details = """23 July, 8 August
24 - 28 July
23 - 31 July
Artistic Gymnastics
Artistic Swimming
Athletics
Badminton
Baseball/Softball
Basketball
Beach Volleyball
Boxing
Canoe Slalom
Canoe Sprint
Cycling BMX Freestyle
Cycling BMX Racing
Cycling Mountain Bike
Cycling Road
Cycling Track
Diving
Equestrian
Fencing""".split("\n").toTypedArray()

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
        var itemTitle: TextView
        var itemDetails: TextView
        var context: Context

        init {
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDetails = itemView.findViewById(R.id.item_detail)
            context = itemView.context
            itemView.setOnClickListener {
                val navController = Navigation.findNavController(itemView)
                val position = adapterPosition
                completeViewModel.adapterPosition.value = position
                navController.navigate(R.id.action_cardViewFragment_to_selectedFragment)
            }
        }
    }

    init {
        completeViewModel =
                ViewModelProvider((context as FragmentActivity)).get(CompleteViewModel::class.java)
    }
}