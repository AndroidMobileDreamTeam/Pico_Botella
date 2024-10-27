package com.example.spin_bottle.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spin_bottle.model.Challenge
import com.example.spin_bottle.view.adapter.ChallengeAdapter
import com.example.spin_bottle.view.dialog.ChallengeDialog
import com.example.spin_bottle.viewmodel.ChallengesViewModel
import com.example.spin_bottle_app.databinding.FragmentChallengesBinding

class ChallengesFragment : Fragment() {
    private lateinit var bindingFCB: FragmentChallengesBinding
    private val challengeViewModel: ChallengesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingFCB = FragmentChallengesBinding.inflate(inflater)
        bindingFCB.lifecycleOwner = this
        return bindingFCB.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controllerFCB()
        observerViewModel()
    }

    private fun controllerFCB() {
        val onSaveCallback: (Challenge) -> Unit = { challenge ->
            challengeViewModel.saveChallenge(challenge)
        }

        bindingFCB.addChallengeBtn.setOnClickListener {
            val challengeDialog = ChallengeDialog(requireContext(), null, onSaveCallback)
            challengeDialog.show()
        }
    }

    private fun observerViewModel() {
        observerChallengesList()
        // observerProgress()
    }

    private fun observerChallengesList() {
        challengeViewModel.getChallengesList()
        challengeViewModel.challengesList.observe(viewLifecycleOwner) { challengesList ->
            val recycler = bindingFCB.recyclerview
            val layoutManager = LinearLayoutManager(context)

            recycler.layoutManager = layoutManager

            val adapter = ChallengeAdapter(challengesList, findNavController(), challengeViewModel)
            recycler.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }

    private fun observerProgress() {
        challengeViewModel.progressState.observe(viewLifecycleOwner) { status ->
            bindingFCB.progress.isVisible = status
        }
    }
}