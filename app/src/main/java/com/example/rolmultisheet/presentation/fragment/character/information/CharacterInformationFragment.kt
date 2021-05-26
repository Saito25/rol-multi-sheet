package com.example.rolmultisheet.presentation.fragment.character.information

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.rolmultisheet.R

class CharacterInformationFragment : Fragment() {

    companion object {
        fun newInstance() = CharacterInformationFragment()
    }

    private lateinit var viewModel: CharacterInformationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.character_information_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CharacterInformationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}