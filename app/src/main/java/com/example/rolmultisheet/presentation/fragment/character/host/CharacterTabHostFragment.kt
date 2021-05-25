package com.example.rolmultisheet.presentation.fragment.character.host

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.rolmultisheet.R

class CharacterTabHostFragment : Fragment() {

    companion object {
        fun newInstance() = CharacterTabHostFragment()
    }

    private lateinit var viewModel: CharacterTabHostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.character_tab_host_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CharacterTabHostViewModel::class.java)
        // TODO: Use the ViewModel
    }

}