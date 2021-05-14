package com.example.rolmultisheet.presentation.fragment.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.rolmultisheet.R
import com.example.rolmultisheet.databinding.HomeFragmentBinding
import com.example.rolmultisheet.presentation.util.fragment.AppBarConfigurationOwner
import com.example.rolmultisheet.presentation.util.fragment.viewBinding

class HomeFragment : Fragment(R.layout.home_fragment) {

    private val binding: HomeFragmentBinding by viewBinding {
        HomeFragmentBinding.bind(it)
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