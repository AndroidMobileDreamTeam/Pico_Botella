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
import com.example.spin_bottle.view.adapter.ChallengeActionListener
import com.example.spin_bottle.view.adapter.ChallengeAdapter
import com.example.spin_bottle.view.dialog.ChallengeDialog
import com.example.spin_bottle.viewmodel.ChallengesViewModel
import com.example.spin_bottle_app.R
import com.example.spin_bottle_app.databinding.FragmentChallengesBinding

class ChallengesFragment : Fragment(), ChallengeActionListener {
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
        setupToolBar()
        onCreateChallenge()
        observerViewModel()
    }

    private fun observerViewModel() {
        observerProgress()
        observerChallengesList()
    }

    private fun observerChallengesList() {
        challengeViewModel.getChallengesList()
        challengeViewModel.challengesList.observe(viewLifecycleOwner) { challengesList ->
            val recycler = bindingFCB.recyclerview
            val layoutManager = LinearLayoutManager(context)

            recycler.layoutManager = layoutManager

            val adapter = ChallengeAdapter(challengesList, this)
            recycler.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }

    private fun observerProgress() {
        challengeViewModel.progressState.observe(viewLifecycleOwner) { status ->
            bindingFCB.progress.isVisible = status
        }
    }

    override fun onCreateChallenge() {
        bindingFCB.addChallengeBtn.setOnClickListener {
            val dialog = ChallengeDialog(requireContext(), null) { newChallenge ->
                challengeViewModel.saveChallenge(newChallenge)
            }
            dialog.show()
        }
    }

    override fun onEditChallenge(challenge: Challenge) {
        val dialog = ChallengeDialog(requireContext(), challenge) { updatedChallenge ->
            challengeViewModel.updateChallenge(updatedChallenge)
        }
        dialog.show()
    }

    override fun onDeleteChallenge(challenge: Challenge) {
        val dialog = ChallengeDialog(requireContext(), challenge) {
            challengeViewModel.deleteChallenge(challenge)
        }
        dialog.showStandard()
    }

    private fun setupToolBar() {
        bindingFCB.contentToolbar.toolbarTitle.text = getString(R.string.challenge_title)
        val button = bindingFCB.contentToolbar.toolbarBackIcon
        button.setOnClickListener {
            returnToHome()
        }
    }

    private fun returnToHome() {
        findNavController().navigate(R.id.action_challengesFragment_to_homeFragment)
    }

}