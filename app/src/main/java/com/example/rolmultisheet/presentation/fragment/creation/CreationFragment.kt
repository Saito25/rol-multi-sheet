package com.example.rolmultisheet.presentation.fragment.creation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.rolmultisheet.R

class CreationFragment : Fragment() {

    companion object {
        fun newInstance() = CreationFragment()
    }

    private lateinit var viewModel: CreationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.creation_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}