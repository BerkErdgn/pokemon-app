package com.berkerdgn.pokemon_app.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.berkerdgn.pokemon_app.R
import com.berkerdgn.pokemon_app.databinding.FragmentDetailBinding
import com.berkerdgn.pokemon_app.util.Resource
import com.berkerdgn.pokemon_app.viewmodel.DetailViewModel


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: DetailViewModel

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
        viewModel = ViewModelProvider(requireActivity()).get(DetailViewModel::class.java)

        viewModel.accessibleGetPokemonDetail("ditto")
        viewModel.pokemonDetails.observe(viewLifecycleOwner, Observer {
            if (it.data != null){
                println(it.data)
            }else{
                println(it.message)
            }
        })

    }

}