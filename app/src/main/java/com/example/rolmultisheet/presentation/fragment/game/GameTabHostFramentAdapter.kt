package com.example.rolmultisheet.presentation.fragment.game

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.rolmultisheet.presentation.fragment.race.main.RaceMainFragment

private const val NUMBER_OF_FRAGMENTS = 1

class GameTabHostFragmentAdapter(parentFragment: Fragment) :
    FragmentStateAdapter(parentFragment) {

    override fun getItemCount(): Int = NUMBER_OF_FRAGMENTS

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> RaceMainFragment()
            else -> throw IndexOutOfBoundsException("Invalid fragment index")
        }

}