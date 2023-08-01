package com.berkerdgn.pokemon_app.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class PokemonFragmentFactory @Inject constructor(
    private val glide: RequestManager
)  : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            DetailFragment::class.java.name->DetailFragment(glide)
            else->super.instantiate(classLoader, className)
        }
    }

}