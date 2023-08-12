package com.berkerdgn.pokemon_app.adapter

import android.animation.ObjectAnimator
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.berkerdgn.pokemon_app.R
import com.berkerdgn.pokemon_app.model.for_detail_model.Ability
import com.berkerdgn.pokemon_app.model.for_detail_model.AbilityX
import com.berkerdgn.pokemon_app.model.for_detail_model.Stat
import com.berkerdgn.pokemon_app.model.for_detail_model.StatX
import com.berkerdgn.pokemon_app.model.room_model.ComparisonAbility
import com.berkerdgn.pokemon_app.model.room_model.ComparisonPokemonModel
import com.berkerdgn.pokemon_app.model.room_model.ComparisonStat
import com.berkerdgn.pokemon_app.viewmodel.SavedViewModel
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject
import kotlin.coroutines.coroutineContext


class ComparisonAdapter : RecyclerView.Adapter<ComparisonAdapter.ComparisonViewHolder>() {

    private lateinit var mContext: Context


    class ComparisonViewHolder(itemView :View ) : RecyclerView.ViewHolder(itemView)

    private val diffUtil = object : DiffUtil.ItemCallback<ComparisonPokemonModel>(){
        override fun areItemsTheSame(
            oldItem: ComparisonPokemonModel,
            newItem: ComparisonPokemonModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ComparisonPokemonModel,
            newItem: ComparisonPokemonModel
        ): Boolean {
            return oldItem == newItem
        }

    }

    private val recyclerListDiffUtil = AsyncListDiffer(this,diffUtil)


    var savedPokemons : List<ComparisonPokemonModel>
        get() = recyclerListDiffUtil.currentList
        set(value) = recyclerListDiffUtil.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComparisonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comparison_recyclerview_raw,parent,false)
        mContext = parent.context

        return ComparisonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  savedPokemons.size
    }

    override fun onBindViewHolder(holder: ComparisonViewHolder, position: Int) {
        val comparisonImage = holder.itemView.findViewById<ImageView>(R.id.comparisonPokemonImageView)

        val comparisonWeightProgressBar = holder.itemView.findViewById<ProgressBar>(R.id.comparisonWeightProgressBar)
            comparisonWeightProgressBar.max = 100
        ObjectAnimator.ofInt(comparisonWeightProgressBar,"progress",savedPokemons[position].weight)
            .start()

        val comparisonHeightProgressBar = holder.itemView.findViewById<ProgressBar>(R.id.comparisonHeightProgressBar)
            comparisonHeightProgressBar.max = 100
        ObjectAnimator.ofInt(comparisonHeightProgressBar,"progress",savedPokemons[position].height)
            .start()


        val comparisonStatesRecyclerView = holder.itemView.findViewById<RecyclerView>(R.id.comparisonStatesRecyclerView)
        val comparisonAbilityRecyclerView = holder.itemView.findViewById<RecyclerView>(R.id.comparisonAbilityRecyclerView)

        val statesAdapter = StatesRecyclerAdapter()
        comparisonStatesRecyclerView.adapter = statesAdapter
        comparisonStatesRecyclerView.layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false)
        statesAdapter.states = convertStates(savedPokemons[position].stats)

        val abilityRecyclerAdapter = AbilityRecyclerAdapter()
        comparisonAbilityRecyclerView.adapter = abilityRecyclerAdapter
        comparisonAbilityRecyclerView.layoutManager = GridLayoutManager(mContext,3)
        abilityRecyclerAdapter.pokemonAbilities = convertAbility(savedPokemons[position].abilities)


    }

    private fun convertStates(comparisonStateList: List<ComparisonStat>): List<Stat>{
        return comparisonStateList.map {
            Stat(
            base_stat=it.base_stat,
            effort= it.effort,
            stat= StatX(it.stat.name,it.stat.url)
            )
        }
    }

    private fun convertAbility(comparisonAbilityList: List<ComparisonAbility>): List<Ability>{
        return comparisonAbilityList.map {
            Ability(
                ability= AbilityX(it.ability.name,it.ability.url),
                is_hidden= it.is_hidden,
                slot= it.slot
            )
        }
    }



}