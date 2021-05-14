package com.example.rolmultisheet.presentation.fragment.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.rolmultisheet.R
import com.example.rolmultisheet.databinding.FragmentHomeBinding
import com.example.rolmultisheet.presentation.util.fragment.AppBarConfigurationOwner
import com.example.rolmultisheet.presentation.util.fragment.viewBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding {
        FragmentHomeBinding.bind(it)
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
        binding.tbHome.run{
            setupWithNavController(navController,
                (requireActivity() as AppBarConfigurationOwner).appBarConfiguration)
            inflateMenu(R.menu.main_nav)
        }
    }

}