package com.thepyprogrammer.gaitanalyzer.ui.main.freeze.freezeData

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.thepyprogrammer.gaitanalyzer.R

open class FreezeViewHolder(itemView: View, completeViewModel: CompleteViewModel) : RecyclerView.ViewHolder(itemView) {
    var itemDate: TextView = itemView.findViewById(R.id.dateView)
    var itemFreeze: TextView = itemView.findViewById(R.id.freezeView)
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