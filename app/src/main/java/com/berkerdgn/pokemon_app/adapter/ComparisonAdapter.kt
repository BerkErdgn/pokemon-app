package com.berkerdgn.pokemon_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.berkerdgn.pokemon_app.R
import com.berkerdgn.pokemon_app.model.for_detail_model.PokemonDetailModel

class ComparisonAdapter : RecyclerView.Adapter<ComparisonAdapter.ComparisonViewHolder>() {
    class ComparisonViewHolder(itemView :View ) : RecyclerView.ViewHolder(itemView)

    private val diffUtil = object : DiffUtil.ItemCallback<PokemonDetailModel>(){
        override fun areItemsTheSame(
            oldItem: PokemonDetailModel,
            newItem: PokemonDetailModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: PokemonDetailModel,
            newItem: PokemonDetailModel
        ): Boolean {
            return oldItem == newItem
        }

    }

    private val recyclerListDiffUtil = AsyncListDiffer(this,diffUtil)


    var savedPokemons : List<PokemonDetailModel>
        get() = recyclerListDiffUtil.currentList
        set(value) = recyclerListDiffUtil.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComparisonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comparison_recyclerview_raw,parent,false)
        return ComparisonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  savedPokemons.size
    }

    override fun onBindViewHolder(holder: ComparisonViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}