package com.berkerdgn.pokemon_app.adapter

import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.berkerdgn.pokemon_app.R
import com.berkerdgn.pokemon_app.model.for_detail_model.Stat

class StatesRecyclerAdapter() :RecyclerView.Adapter<StatesRecyclerAdapter.StatesViewHolder>(){
    class StatesViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView)


    private val diffUtil = object : DiffUtil.ItemCallback<Stat>(){
        override fun areItemsTheSame(oldItem: Stat, newItem: Stat): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Stat, newItem: Stat): Boolean {
            return oldItem == newItem
        }

    }

    private val recyclerDiffUtil = AsyncListDiffer(this,diffUtil)

    var states : List<Stat>
        get() = recyclerDiffUtil.currentList
        set(value) = recyclerDiffUtil.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.states_recycler_raw,parent,false)
        return StatesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return states.size
    }

    override fun onBindViewHolder(holder: StatesViewHolder, position: Int) {
        val stateName = holder.itemView.findViewById<TextView>(R.id.stateTextView2)
        val stateProgressBar = holder.itemView.findViewById<ProgressBar>(R.id.stateProgressBar)
        stateProgressBar.max = 100
        ObjectAnimator.ofInt(stateProgressBar,"progress",states[position].base_stat)
            .start()
        holder.itemView.apply {
            stateName.text = "${states[position].stat.name} : "
        }





    }


}