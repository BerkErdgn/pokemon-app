package com.berkerdgn.pokemon_app.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.berkerdgn.pokemon_app.R
import com.berkerdgn.pokemon_app.adapter.ComparisonAdapter
import com.berkerdgn.pokemon_app.databinding.FragmentComparisonBinding
import com.berkerdgn.pokemon_app.viewmodel.SavedViewModel


class ComparisonFragment : Fragment() {

    private var _binding : FragmentComparisonBinding?= null
    private val binding get() = _binding!!
    lateinit var savedViewModel: SavedViewModel
    private var comparisonAdapter : ComparisonAdapter = ComparisonAdapter()


    private val swipeCallBack = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutPosition = viewHolder.layoutPosition
            val selectedPokemon = comparisonAdapter.savedPokemons[layoutPosition]
            savedViewModel.deleteSavedPokemon(selectedPokemon)
        }

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentComparisonBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        savedViewModel = ViewModelProvider(requireActivity()).get(SavedViewModel::class.java)
        savedViewModel.getSavedPokemon()

        binding.comparisonRecyclerView.adapter = comparisonAdapter
        binding.comparisonRecyclerView.layoutManager = GridLayoutManager(requireContext(),1)
        ItemTouchHelper(swipeCallBack).attachToRecyclerView(binding.comparisonRecyclerView)

        observeLiveDataForSavedPokemon()

    }

    private fun observeLiveDataForSavedPokemon(){
        savedViewModel.savedPokemon.observe(viewLifecycleOwner, Observer {
            binding.emptyTextView2.visibility = View.GONE
            binding.comparisonProgressBar.visibility = View.VISIBLE
            if (it.isNotEmpty()){
                binding.emptyTextView2.visibility = View.GONE
                binding.comparisonProgressBar.visibility = View.GONE
                comparisonAdapter.savedPokemons = it
            }else{
                binding.emptyTextView2.visibility = View.VISIBLE
                binding.comparisonProgressBar.visibility = View.GONE

            }

        })
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }




}