package com.example.pico_botella.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.spin_bottle_app.R



import androidx.core.view.isVisible

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spin_bottle_app.databinding.FragmentChallengesBinding
import com.example.pico_botella.view.adapter.AdapterDeEjemplo
import com.example.pico_botella.viewmodel.ViewModelDeEjemplo

class ChallengesFragment : Fragment() {
    private lateinit var binding: FragmentChallengesBinding
    // private val viewModelDeEjemplo: ViewModelDeEjemplo by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChallengesBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // controladores()
        // observadorViewModel()
    }
}