package com.example.pico_botella.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pico_botella.view.adapter.ChallengeAdapter
import com.example.spin_bottle_app.databinding.FragmentChallengesBinding
import com.example.pico_botella.viewmodel.ChallengesViewModel

import com.example.spin_bottle_app.databinding.ItemChallengesBinding

class ChallengesFragment : Fragment() {
    private lateinit var bindingFCB: FragmentChallengesBinding
    private val challengeViewModel: ChallengesViewModel by viewModels()

    private lateinit var bindingICB: ItemChallengesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingFCB = FragmentChallengesBinding.inflate(inflater)
        bindingFCB.lifecycleOwner = this
        return bindingFCB.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controladoresFCB()
        controladoresICB()
        // observadorViewModel()
    }

    // Esto creo que va aca pero no estoy 100% seguro, quizas va en el ChallengeViewHolder (aunque no creo) // <============================== VEAN ESTO
    private fun controladoresFCB() {
        bindingFCB.addChallengeBtn.setOnClickListener {
            // Codigo que guie a la ventana para añadir retos // <============================== VEAN ESTO
        }
    }

    // Esto no se si va aca o en el ChallengeViewHolder // <============================== VEAN ESTO
    private fun controladoresICB() {
        bindingICB.editChallengeButton.setOnClickListener {
            // Codigo que guie a la ventana para editar reto // <============================== VEAN ESTO
        }
        bindingICB.deleteChallengeButton.setOnClickListener {
            // Codigo que guie a la ventana para eliminar reto // <============================== VEAN ESTO
        }
    }

    private fun observadorViewModel(){
        observerChallengesList()
        // observerProgress()
    }

    private fun observerChallengesList(){
        challengeViewModel.getChallengesList()
        challengeViewModel.challengesList.observe(viewLifecycleOwner) { challengesList ->
            val recycler = bindingFCB.recyclerview
            val layoutManager = LinearLayoutManager(context)

            recycler.layoutManager = layoutManager

            val adapter = ChallengeAdapter(challengesList, findNavController())
            recycler.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }

    private fun observerProgress(){
        challengeViewModel.progresState.observe(viewLifecycleOwner){status ->
            bindingFCB.progress.isVisible = status
        }
    }

}