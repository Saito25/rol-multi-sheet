package com.example.rolmultisheet.presentation.fragment.race.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.rolmultisheet.R
import com.example.rolmultisheet.data.database.AppDatabase
import com.example.rolmultisheet.data.repository.RoomRepository
import com.example.rolmultisheet.databinding.CommonListFragmentBinding
import com.example.rolmultisheet.presentation.util.fragment.viewBinding

class RaceMainFragment : Fragment(R.layout.common_list_fragment) {

    private val binding: CommonListFragmentBinding by viewBinding {
        CommonListFragmentBinding.bind(it)
    }

    private val viewModel: RaceMainViewModel by viewModels {
        RaceMainViewModelFactory(
            RoomRepository(
                AppDatabase.getInstance(requireContext()).appDao
            )
        )
    }

    private val listAdapter: RaceMainListAdapter by lazy {
        RaceMainListAdapter()
    }
}