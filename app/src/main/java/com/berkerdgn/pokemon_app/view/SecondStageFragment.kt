package com.berkerdgn.pokemon_app.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.berkerdgn.pokemon_app.R
import com.berkerdgn.pokemon_app.databinding.ActivityMainBinding
import com.berkerdgn.pokemon_app.databinding.FragmentSecondStageBinding


class SecondStageFragment : Fragment() {

    private var _binding : FragmentSecondStageBinding?= null
    private val binding get () = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSecondStageBinding.inflate(inflater,container,false)
        replaceFragment(HomeFragment())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding.navBar.setOnItemSelectedListener {
            when (it){
                R.id.home -> replaceFragment(HomeFragment())
                R.id.search -> replaceFragment(SearchFragment())
            }
            true
        }

    }

    private fun replaceFragment (fragment: Fragment){
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.meanFrameLayout, fragment)
        fragmentTransaction.commit()

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }


}