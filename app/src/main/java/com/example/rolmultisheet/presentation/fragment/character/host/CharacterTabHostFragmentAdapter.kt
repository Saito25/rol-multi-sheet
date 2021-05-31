package com.example.rolmultisheet.presentation.fragment.character.host

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.rolmultisheet.presentation.fragment.character.information.CharacterInformationFragment

private const val NUMBER_OF_FRAGMENTS = 2

class CharacterTabHostFragmentAdapter(parentFragment: Fragment) :
    FragmentStateAdapter(parentFragment) {

    override fun getItemCount(): Int = NUMBER_OF_FRAGMENTS

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> CharacterInformationFragment()
            1 -> CharacterInformationFragment()
            else -> throw IndexOutOfBoundsException("Invalid fragment index")
        }
}