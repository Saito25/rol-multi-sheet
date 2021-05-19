package com.example.rolmultisheet.presentation.fragment.game

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.rolmultisheet.R
import com.example.rolmultisheet.databinding.GameTabHostFragmentBinding
import com.example.rolmultisheet.presentation.util.fragment.viewBinding
import com.example.rolmultisheet.presentation.util.tab.PageContainer
import com.example.rolmultisheet.presentation.util.tab.PageFragment
import com.google.android.material.tabs.TabLayoutMediator
import java.lang.ref.WeakReference

class GameTabHostFragment : Fragment(R.layout.game_tab_host_fragment), PageContainer {

    private val binding: GameTabHostFragmentBinding by viewBinding {
        GameTabHostFragmentBinding.bind(it)
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
        setupTabLayoutMediator()
        setupFab()
    }

    private fun setupToolBar() {
        binding.tbGameHost.run {
            setupWithNavController(navController)
        }
    }

    private fun setupViewPager() {
        binding.pagerGameTabHost.adapter = GameTabHostFragmentAdapter(this)
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
        TabLayoutMediator(binding.tabGameTabHost, binding.pagerGameTabHost) { tab, position ->
            tab.setText(titleResIds[position])
        }.attach()
    }

    private fun setupFab() {
        binding.fabGameTabHost.setOnClickListener { onFabClick() }
    }

    private fun onFabClick() {
        currentPage?.get()?.onFabClick()
    }
}