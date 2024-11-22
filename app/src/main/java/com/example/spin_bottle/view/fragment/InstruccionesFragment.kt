package com.example.spin_bottle.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.spin_bottle_app.R
import com.example.spin_bottle_app.databinding.FragmentInstruccionesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InstruccionesFragment : Fragment() {

    private lateinit var binding: FragmentInstruccionesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInstruccionesBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolBar()
    }

    private fun setupToolBar() {
        binding.contentToolbar.toolbarTitle.text = getString(R.string.reglas_title)
        val button = binding.contentToolbar.toolbarBackIcon
        button.setOnClickListener {
            returnToHome()
        }
    }

    private fun returnToHome() {
        findNavController().navigate(R.id.action_instruccionesFragment_to_homeFragment)
    }

}