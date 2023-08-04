package com.berkerdgn.pokemon_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.berkerdgn.pokemon_app.R
import com.berkerdgn.pokemon_app.model.for_detail_model.Ability

class AbilityRecyclerAdapter : RecyclerView.Adapter<AbilityRecyclerAdapter.AbilityViewHolder>(){
    class AbilityViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffUtil = object : DiffUtil.ItemCallback<Ability>(){
        override fun areItemsTheSame(oldItem: Ability, newItem: Ability): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Ability, newItem: Ability): Boolean {
            return oldItem == newItem
        }

    }

    private val recyclerListDiffUtil = AsyncListDiffer(this,diffUtil)

    var pokemonAbilities : List<Ability>
        get() = recyclerListDiffUtil.currentList
        set(value) = recyclerListDiffUtil.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ability_recycler_raw,parent,false)
        return AbilityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemonAbilities.size
    }

    override fun onBindViewHolder(holder: AbilityViewHolder, position: Int) {
        val abilityName = holder.itemView.findViewById<TextView>(R.id.abilityTextView2)
        holder.itemView.apply {
            abilityName.text = pokemonAbilities[position].ability.name
        }
    }
}