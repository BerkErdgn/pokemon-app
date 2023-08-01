package com.berkerdgn.pokemon_app.view

import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.berkerdgn.pokemon_app.R
import com.berkerdgn.pokemon_app.adapter.StatesRecyclerAdapter
import com.berkerdgn.pokemon_app.databinding.FragmentDetailBinding
import com.berkerdgn.pokemon_app.util.Resource
import com.berkerdgn.pokemon_app.util.Status
import com.berkerdgn.pokemon_app.viewmodel.DetailViewModel
import com.bumptech.glide.RequestManager
import javax.inject.Inject


class DetailFragment @Inject constructor(
    val glide : RequestManager
) : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: DetailViewModel
    lateinit var pokemonName : String
    private var statesRecyclerAdapter : StatesRecyclerAdapter = StatesRecyclerAdapter()

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

        val progressBar =binding.heightProgressBar
            progressBar.max = 10


        val currentProgress = 6
        ObjectAnimator.ofInt(progressBar,"progress", currentProgress )
            .start()

        observeLiveDataForDetails(pokemonName = pokemonName)


    }

    private fun observeLiveDataForDetails(pokemonName : String){
        viewModel.accessibleGetPokemonDetail(pokemonName = pokemonName )
        viewModel.pokemonDetails.observe(viewLifecycleOwner, Observer {

            when (it.status){
                Status.SUCCESS->{
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
                }
                Status.ERROR->{
                    println(it.message)
                }
                Status.LOADING->{

                }
            }

        })
    }



}