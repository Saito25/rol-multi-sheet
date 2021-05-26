package com.example.rolmultisheet.presentation.fragment.character.host

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.example.rolmultisheet.R
import com.example.rolmultisheet.data.database.AppDatabase
import com.example.rolmultisheet.data.repository.RoomRepository
import com.example.rolmultisheet.databinding.CharacterTabHostFragmentBinding
import com.example.rolmultisheet.presentation.util.fragment.viewBinding
import com.example.rolmultisheet.presentation.util.tab.PageContainer
import com.example.rolmultisheet.presentation.util.tab.PageFragment
import com.example.rolmultisheet.presentation.util.tab.onAddTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import java.lang.ref.WeakReference

class CharacterTabHostFragment : Fragment(R.layout.character_tab_host_fragment), PageContainer {

    private val binding: CharacterTabHostFragmentBinding by viewBinding {
        CharacterTabHostFragmentBinding.bind(it)
    }

    private val viewModel: CharacterTabHostViewModel by viewModels {
        CharacterTabHostViewModelFactory(
            RoomRepository(AppDatabase.getInstance(requireContext()).appDao),
            args.characterId
        )
    }

    private val navController: NavController by lazy { findNavController() }

    private val args: CharacterTabHostFragmentArgs by navArgs()

    override var currentPage: WeakReference<PageFragment>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupToolBar()
        setupFab()
        setupViewPager()
        setupTabLayout()
        setupTabLayoutMediator()
    }

    private fun setupToolBar() {
        binding.toolbarCharacterTab.run {
            setupWithNavController(navController)
            viewModel.character.observe(viewLifecycleOwner) {
                if (it != null) {
                    title = it.characterName
                }
            }
        }
    }

    private fun setupFab() {
        updateFab(0)
        binding.fabCharacterTab.setOnClickListener { onFabClick() }
    }

    private fun setupViewPager() {
        binding.pagerCharacterTab.adapter = CharacterTabHostFragmentAdapter(this)
    }

    private fun setupTabLayout() {
        binding.tabLayoutCharacterTab.onAddTabSelectedListener {
            updateFab(it.position)
        }
    }

    private fun setupTabLayoutMediator() {
        val titleResIds = intArrayOf(
            R.string.character_host_information_name,
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

    private fun updateFab(currentItem: Int) {
        if (currentItem != 0) {
            binding.fabCharacterTab.visibility = View.VISIBLE
        } else {
            binding.fabCharacterTab.visibility = View.GONE
        }
    }

    private fun onFabClick() {
        currentPage?.get()?.onFabClick()
    }

}