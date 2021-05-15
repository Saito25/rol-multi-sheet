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

class GameTabHostFragment : Fragment(R.layout.game_tab_host_fragment) {

    private val binding: GameTabHostFragmentBinding by viewBinding {
        GameTabHostFragmentBinding.bind(it)
    }

    private val navController: NavController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupToolBar()
    }

    private fun setupToolBar() {
        binding.tbGameHost.run {
            setupWithNavController(navController)
        }
    }
}