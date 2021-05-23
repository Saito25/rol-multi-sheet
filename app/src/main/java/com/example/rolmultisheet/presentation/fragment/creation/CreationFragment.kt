package com.example.rolmultisheet.presentation.fragment.creation

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.rolmultisheet.R
import com.example.rolmultisheet.data.database.AppDatabase
import com.example.rolmultisheet.data.repository.RoomRepository
import com.example.rolmultisheet.databinding.CreationFragmentBinding
import com.example.rolmultisheet.presentation.util.fragment.viewBinding

class CreationFragment : Fragment(R.layout.creation_fragment) {

    private val binding: CreationFragmentBinding by viewBinding {
        CreationFragmentBinding.bind(it)
    }

    private val viewModel: CreationViewModel by viewModels {
        CreationViewModelFactory(
            RoomRepository(
                AppDatabase.getInstance(requireContext()).appDao
            )
        )
    }
}