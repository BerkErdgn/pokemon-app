package com.berkerdgn.pokemon_app.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.berkerdgn.pokemon_app.R
import com.berkerdgn.pokemon_app.databinding.FragmentSearchBinding
import com.berkerdgn.pokemon_app.util.Status
import com.berkerdgn.pokemon_app.viewmodel.DetailViewModel
import com.berkerdgn.pokemon_app.viewmodel.SearchViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SearchFragment : Fragment() {

    private var _binding : FragmentSearchBinding? = null

    private val binding get() = _binding!!
    lateinit var  viewModel : SearchViewModel
    private var pokemonName = "ditto"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)

        binding.searchProgressBar.visibility = View.GONE
        binding.searchPokemonNameTextView.visibility = View.GONE
        binding.searchPokemonImageView2.visibility = View.GONE

        binding.searchViewText.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
               if (query ==""){
                   Toast.makeText(context,"Pleas Enter Pokemon Name !!", Toast.LENGTH_LONG).show()
               }else{
                   viewModel.accessibleGetSearchPokemon(query!!)
                   observeLiveDataForSearch()
               }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })

        binding.pokemonLayout.setOnClickListener {
            val action = SecondStageFragmentDirections.actionSecondStageFragmentToDetailFragment2(pokemonName)
            Navigation.findNavController(view).navigate(action)
        }


    }


    fun observeLiveDataForSearch(){
        viewModel.searchPokemon.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS->{
                    pokemonName = it.data!!.name
                    binding.searchPokemonNameTextView.text = it.data!!.name
                    binding.searchProgressBar.visibility = View.GONE
                    binding.searchPokemonNameTextView.visibility = View.VISIBLE
                    binding.searchPokemonImageView2.visibility = View.VISIBLE

                }
                Status.LOADING->{
                    binding.searchProgressBar.visibility = View.VISIBLE
                    binding.searchPokemonNameTextView.visibility = View.GONE
                    binding.searchPokemonImageView2.visibility = View.GONE
                }
                Status.ERROR->{
                    binding.searchProgressBar.visibility = View.VISIBLE
                    binding.searchPokemonNameTextView.visibility = View.GONE
                    binding.searchPokemonImageView2.visibility = View.GONE
                }
            }
        })
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}