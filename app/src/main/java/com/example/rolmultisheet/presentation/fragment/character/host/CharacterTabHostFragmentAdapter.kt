package com.example.rolmultisheet.presentation.fragment.character.host

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.rolmultisheet.presentation.fragment.character.armour.list.CharacterArmourListFragment
import com.example.rolmultisheet.presentation.fragment.character.information.CharacterInformationFragment
import com.example.rolmultisheet.presentation.fragment.character.item.list.CharacterItemListFragment
import com.example.rolmultisheet.presentation.fragment.character.spell.list.CharacterSpellListFragment
import com.example.rolmultisheet.presentation.fragment.character.weapon.list.CharacterWeaponListFragment

private const val NUMBER_OF_FRAGMENTS = 5

class CharacterTabHostFragmentAdapter(parentFragment: Fragment) :
    FragmentStateAdapter(parentFragment) {

    override fun getItemCount(): Int = NUMBER_OF_FRAGMENTS

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> CharacterInformationFragment()
            1 -> CharacterSpellListFragment()
            2 -> CharacterItemListFragment()
            3 -> CharacterArmourListFragment()
            4 -> CharacterWeaponListFragment()
            else -> throw IndexOutOfBoundsException("Invalid fragment index")
        }
}