package com.example.rolmultisheet.presentation.fragment.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rolmultisheet.R
import com.example.rolmultisheet.databinding.HomeFragmentBinding
import com.example.rolmultisheet.presentation.util.fragment.AppBarConfigurationOwner
import com.example.rolmultisheet.presentation.util.fragment.viewBinding

class HomeFragment : Fragment(R.layout.home_fragment) {

    private val binding: HomeFragmentBinding by viewBinding {
        HomeFragmentBinding.bind(it)
    }

    private val viewModel: HomeViewModel by viewModels()

    private val listAdapter: HomeListAdapter by lazy {
        HomeListAdapter()
    }

    private val navController: NavController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupToolBar()
        setupRecyclerView()
    }

    private fun setupToolBar() {
        binding.tbHome.run {
            setupWithNavController(
                navController,
                (requireActivity() as AppBarConfigurationOwner).appBarConfiguration
            )
            inflateMenu(R.menu.main_nav)
        }
    }

    private fun setupRecyclerView() {
        binding.listHomeCharacters.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
            itemAnimator = DefaultItemAnimator()
            adapter = listAdapter
        }
    }

}