package com.berkerdgn.pokemon_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.berkerdgn.pokemon_app.R
import com.berkerdgn.pokemon_app.model.PokemonResult


class PokemonsRecyclerAdapter() : RecyclerView.Adapter<PokemonsRecyclerAdapter.PokemonsRecyclerViewHolder>()  {
    class PokemonsRecyclerViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView)

    private val diffUtil = object : DiffUtil.ItemCallback<PokemonResult>(){
        override fun areItemsTheSame(oldItem: PokemonResult, newItem: PokemonResult): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PokemonResult, newItem: PokemonResult): Boolean {
            return oldItem == newItem
        }

    }

    private val recyclerListDiffUtil = AsyncListDiffer(this,diffUtil)

    var pokemons : List<PokemonResult>
        get() = recyclerListDiffUtil.currentList
        set(value) = recyclerListDiffUtil.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonsRecyclerViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemons_recyclerview_row,parent,false)
        return  PokemonsRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onBindViewHolder(holder: PokemonsRecyclerViewHolder, position: Int) {
        val pokemonName = holder.itemView.findViewById<TextView>(R.id.pokemonNameTextView)
        holder.itemView.apply {
            pokemonName.text = pokemons[position].name
        }
    }
}