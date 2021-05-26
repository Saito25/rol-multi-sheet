package com.example.rolmultisheet.presentation.fragment.character.host

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.rolmultisheet.R
import com.example.rolmultisheet.databinding.CharacterTabHostFragmentBinding
import com.example.rolmultisheet.presentation.util.fragment.viewBinding
import com.example.rolmultisheet.presentation.util.tab.PageContainer
import com.example.rolmultisheet.presentation.util.tab.PageFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.lang.ref.WeakReference

class CharacterTabHostFragment : Fragment(R.layout.character_tab_host_fragment), PageContainer {

    private val binding: CharacterTabHostFragmentBinding by viewBinding {
        CharacterTabHostFragmentBinding.bind(it)
    }

    private val navController: NavController by lazy { findNavController() }

    override var currentPage: WeakReference<PageFragment>? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupToolBar()
        setupViewPager()
        setupTabLayout()
        setupTabLayoutMediator()
        setupFab()
    }

    private fun setupToolBar() {
        binding.toolbarCharacterTab.run {
            setupWithNavController(navController)
        }
    }

    private fun setupViewPager() {
        binding.pagerCharacterTab.adapter = CharacterTabHostFragmentAdapter(this)
    }

    private fun setupTabLayout() {
        binding.tabLayoutCharacterTab.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    private fun updateFab(currentItem: Int) {
        if (currentItem != 0) {
            binding.fabCharacterTab.visibility = View.VISIBLE
        } else {
            binding.fabCharacterTab.visibility = View.GONE
        }
    }

    private fun setupTabLayoutMediator() {
        val titleResIds = intArrayOf(
            R.string.game_host_race_name,
            R.string.game_host_class_name,
            R.string.game_host_spell_name,
            R.string.game_host_item_name,
            R.string.game_host_armour_name,
            R.string.game_host_weapon_name,
        )
        TabLayoutMediator(
            binding.tabLayoutCharacterTab,
            binding.pagerCharacterTab
        ) { tab, position ->
            tab.setText(titleResIds[position])
        }.attach()
    }

    private fun setupFab() {
        updateFab(1)
        binding.fabCharacterTab.setOnClickListener { onFabClick() }
    }

    private fun onFabClick() {
        currentPage?.get()?.onFabClick()
    }

}