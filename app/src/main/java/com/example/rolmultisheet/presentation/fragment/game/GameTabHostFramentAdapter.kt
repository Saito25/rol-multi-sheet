package com.example.rolmultisheet.presentation.fragment.game

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.rolmultisheet.presentation.fragment.armour.main.ArmourMainFragment
import com.example.rolmultisheet.presentation.fragment.item.main.ItemMainFragment
import com.example.rolmultisheet.presentation.fragment.job.main.JobMainFragment
import com.example.rolmultisheet.presentation.fragment.race.main.RaceMainFragment
import com.example.rolmultisheet.presentation.fragment.spell.main.SpellMainFragment
import com.example.rolmultisheet.presentation.fragment.weapon.main.WeaponMainFragment

private const val NUMBER_OF_FRAGMENTS = 6

class GameTabHostFragmentAdapter(parentFragment: Fragment) :
    FragmentStateAdapter(parentFragment) {

    override fun getItemCount(): Int = NUMBER_OF_FRAGMENTS

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> RaceMainFragment()
            1 -> JobMainFragment()
            2 -> SpellMainFragment()
            3 -> ItemMainFragment()
            4 -> ArmourMainFragment()
            5 -> WeaponMainFragment()
            else -> throw IndexOutOfBoundsException("Invalid fragment index")
        }

}