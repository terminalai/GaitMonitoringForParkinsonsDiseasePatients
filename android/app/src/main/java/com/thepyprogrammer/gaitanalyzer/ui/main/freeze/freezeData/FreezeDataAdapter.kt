package com.thepyprogrammer.gaitanalyzer.ui.main.freeze.freezeData

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.ui.main.home.HomeViewModel
import com.thepyprogrammer.ktlib.Util
import java.time.*

class FreezeDataAdapter(private val activity: AppCompatActivity, private val homeViewModel: HomeViewModel) :
    RecyclerView.Adapter<FreezeDataAdapter.FreezeDataViewHolder>() {
    private val completeViewModel: CompleteViewModel =
        ViewModelProvider((activity as FragmentActivity)).get(CompleteViewModel::class.java)

    val dates = mutableListOf<String>()
    val freezes = mutableListOf<String>()

    private fun setData(freezesData: MutableList<Long>?) {
        val map = hashMapOf<String, Int?>()
        freezesData?.forEach {
            val date = LocalDateTime.ofInstant(Instant.ofEpochSecond((it / (1000 * 3600 * 24)) * (1000 * 3600 * 24)), ZoneId.systemDefault()).toLocalDate()
            val dateString = Util.dateFormat.format(date)
            map[dateString] =
                if(map.containsKey(dateString))
                    map[dateString]?.plus(1)
                else 1
        }
        map.toSortedMap()

        map.forEach { (date, freeze) ->
            dates.add(date)
            freezes.add(freeze.toString())
        }
    }

    init {
        setData(homeViewModel.freezes.value)

        val freezeObserver = Observer<MutableList<Long>> { freezes ->
            setData(freezes)
            notifyDataSetChanged()
        }

        homeViewModel.freezes.observe(
            activity, freezeObserver
        )


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FreezeDataViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_freeze, parent, false)
        return FreezeDataViewHolder(v)
    }

    override fun onBindViewHolder(holder: FreezeDataViewHolder, position: Int) {
        holder.itemDate.text = dates[position]
        holder.itemFreeze.text = freezes[position]
    }

    override fun getItemCount(): Int {
        return dates.size
    }

    inner class FreezeDataViewHolder(itemView: View) : FreezeViewHolder(itemView, completeViewModel)

}