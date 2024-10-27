package com.example.spin_bottle.view.viewholder

import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.spin_bottle.model.Challenge
import com.example.spin_bottle.view.dialog.ChallengeDialog
import com.example.spin_bottle.viewmodel.ChallengesViewModel
import com.example.spin_bottle_app.databinding.ItemChallengesBinding

class ChallengeViewHolder(
    binding: ItemChallengesBinding, val navController: NavController,
    private val challengesViewModel: ChallengesViewModel
) :
    RecyclerView.ViewHolder(binding.root) {
    private val bindingItem = binding


    fun setItemChallenge(challenge: Challenge) {
        bindingItem.challenge.text = challenge.description

        controllerICB(challenge)
    }

    private fun controllerICB(challenge: Challenge) {
        val onEditCallback: (Challenge) -> Unit = { auxChallenge ->
            challengesViewModel.updateChallenge(auxChallenge)
        }

        bindingItem.editChallengeButton.setOnClickListener {
            val challengeDialog =
                ChallengeDialog(bindingItem.root.context, challenge, onEditCallback)
            challengeDialog.show()
        }

        bindingItem.deleteChallengeButton.setOnClickListener {

        }
    }

}