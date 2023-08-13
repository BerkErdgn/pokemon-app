package com.berkerdgn.pokemon_app.view

import android.animation.ObjectAnimator
import android.graphics.Color
import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.berkerdgn.pokemon_app.R
import com.berkerdgn.pokemon_app.adapter.AbilityRecyclerAdapter
import com.berkerdgn.pokemon_app.adapter.StatesRecyclerAdapter
import com.berkerdgn.pokemon_app.databinding.FragmentDetailBinding
import com.berkerdgn.pokemon_app.model.for_detail_model.Ability
import com.berkerdgn.pokemon_app.model.for_detail_model.PokemonDetailModel
import com.berkerdgn.pokemon_app.model.for_detail_model.Stat
import com.berkerdgn.pokemon_app.model.room_model.ComparisonAbility
import com.berkerdgn.pokemon_app.model.room_model.ComparisonAbilityX
import com.berkerdgn.pokemon_app.model.room_model.ComparisonStat
import com.berkerdgn.pokemon_app.model.room_model.ComparisonStatX
import com.berkerdgn.pokemon_app.util.Resource
import com.berkerdgn.pokemon_app.util.Status
import com.berkerdgn.pokemon_app.viewmodel.DetailViewModel
import com.berkerdgn.pokemon_app.viewmodel.SavedViewModel
import com.bumptech.glide.RequestManager
import javax.inject.Inject


class DetailFragment @Inject constructor(
    val glide : RequestManager
) : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: DetailViewModel
    lateinit var savedViewModel: SavedViewModel
    lateinit var pokemonName : String
    lateinit var savedList : Resource<PokemonDetailModel>
    private var statesRecyclerAdapter : StatesRecyclerAdapter = StatesRecyclerAdapter()
    private var abilityRecyclerAdapter : AbilityRecyclerAdapter = AbilityRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        arguments?.let {
           pokemonName = DetailFragmentArgs.fromBundle(it).pokemonName
        }

        viewModel = ViewModelProvider(requireActivity()).get(DetailViewModel::class.java)

        binding.statesRecyclerView.adapter = statesRecyclerAdapter
        binding.statesRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

        binding.abilityRecyclerView.adapter = abilityRecyclerAdapter
        binding.abilityRecyclerView.layoutManager = GridLayoutManager(requireContext(),3)

        val progressBar =binding.heightProgressBar
            progressBar.max = 10


        val currentProgress = 6
        ObjectAnimator.ofInt(progressBar,"progress", currentProgress )
            .start()

        observeLiveDataForDetails(pokemonName = pokemonName)

        binding.versusActionButton.setOnClickListener {
            savedViewModel = ViewModelProvider(requireActivity()).get(SavedViewModel::class.java)
            when(savedList.status){
                Status.SUCCESS->{
                    val savedPokemon = savedList.data!!
                    val comparisonAbilityList: List<ComparisonAbility> = convertAbilityList(savedPokemon.abilities)
                    val comparisonStatList: List<ComparisonStat> = convertStats(savedPokemon.stats)
                    savedViewModel.savePokemon(abilities = comparisonAbilityList, id = savedPokemon.id, name = savedPokemon.name, weight = savedPokemon.weight, stats = comparisonStatList, height = savedPokemon.height)

                    Toast.makeText(context,"Successfully Saved!", Toast.LENGTH_LONG).show()
                }

                else -> {
                    Toast.makeText(context,"Don't Save!", Toast.LENGTH_LONG).show()
                }
            }

        }
    }

    private fun observeLiveDataForDetails(pokemonName : String){
        viewModel.accessibleGetPokemonDetail(pokemonName = pokemonName )
        viewModel.pokemonDetails.observe(viewLifecycleOwner, Observer {
            savedList = it
            when (it.status){
                Status.SUCCESS->{

                    binding.pokemonImageView.visibility = View.VISIBLE
                    binding.weightTextView2.visibility = View.VISIBLE
                    binding.heightTextView2.visibility = View.VISIBLE
                    binding.weightProgressBar.visibility = View.VISIBLE
                    binding.heightProgressBar.visibility = View.VISIBLE
                    binding.loadingProgressBar.visibility = View.GONE


                    val heightProgressBar =binding.heightProgressBar
                    heightProgressBar.max = 100

                    ObjectAnimator.ofInt(heightProgressBar,"progress", it.data!!.height  )
                        .start()

                    val weightProgressBar =binding.weightProgressBar
                    weightProgressBar.max = 100

                    ObjectAnimator.ofInt(weightProgressBar,"progress", it.data.weight  )
                        .start()

                   glide.load(it.data.sprites.front_default).into(binding.pokemonImageView)

                    statesRecyclerAdapter.states = it.data.stats

                    abilityRecyclerAdapter.pokemonAbilities = it.data.abilities

                }
                Status.ERROR->{
                    println(it.message)
                    binding.pokemonImageView.visibility = View.GONE
                    binding.weightTextView2.visibility = View.GONE
                    binding.heightTextView2.visibility = View.GONE
                    binding.weightProgressBar.visibility = View.GONE
                    binding.heightProgressBar.visibility = View.GONE
                    binding.loadingProgressBar.visibility = View.VISIBLE
                }
                Status.LOADING->{
                    binding.pokemonImageView.visibility = View.GONE
                    binding.weightTextView2.visibility = View.GONE
                    binding.heightTextView2.visibility = View.GONE
                    binding.weightProgressBar.visibility = View.GONE
                    binding.heightProgressBar.visibility = View.GONE
                    binding.loadingProgressBar.visibility = View.VISIBLE

                }
            }

        })
    }


    private fun convertAbilityList(abilityList: List<Ability>): List<ComparisonAbility>{
        return abilityList.map {
            ComparisonAbility(
                ability = ComparisonAbilityX(it.ability.name, it.ability.url),
                is_hidden = it.is_hidden,
                slot = it.slot
            )
        }
    }

    private fun convertStats(stateList: List<Stat>): List<ComparisonStat>{
        return stateList.map {
            ComparisonStat(
                base_stat=it.base_stat ,
                effort=it.effort,
                stat=ComparisonStatX(it.stat.name,it.stat.url)
            )
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}