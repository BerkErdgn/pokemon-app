package com.berkerdgn.pokemon_app.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.berkerdgn.pokemon_app.R
import com.berkerdgn.pokemon_app.adapter.PokemonsRecyclerAdapter
import com.berkerdgn.pokemon_app.databinding.FragmentHomeBinding
import com.berkerdgn.pokemon_app.util.Status
import com.berkerdgn.pokemon_app.viewmodel.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel


class HomeFragment : Fragment() {

    private var _binding :FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val pokemonsRecyclerAdapter : PokemonsRecyclerAdapter = PokemonsRecyclerAdapter()

    lateinit var viewModel : HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

        binding.pokemonsRecyclerView.adapter = pokemonsRecyclerAdapter
        binding.pokemonsRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)

        binding.versusListFloatingActionButton.setOnClickListener {
            val action = SecondStageFragmentDirections.actionSecondStageFragmentToComparisonFragment()
            Navigation.findNavController(view).navigate(action)
        }

        observeLiveDataForPokemons()

    }


    private fun observeLiveDataForPokemons(){
        viewModel.pokemonList.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.LOADING->{
                    binding.pokemonsRecyclerView.visibility = View.GONE
                    binding.loadingProgressBar2.visibility = View.VISIBLE
                }
                Status.SUCCESS->{
                    binding.pokemonsRecyclerView.visibility = View.VISIBLE
                    binding.loadingProgressBar2.visibility = View.GONE
                    pokemonsRecyclerAdapter.pokemons = it.data!!.results
                }
                Status.ERROR->{
                    binding.pokemonsRecyclerView.visibility = View.GONE
                    binding.loadingProgressBar2.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}