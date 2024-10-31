package com.example.spin_bottle.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.spin_bottle.model.Challenge
import com.example.spin_bottle.view.adapter.ChallengeActionListener
import com.example.spin_bottle_app.databinding.ItemChallengesBinding

class ChallengeViewHolder(
    private val binding: ItemChallengesBinding,
    private val listener: ChallengeActionListener
) :
    RecyclerView.ViewHolder(binding.root) {

    fun setItemChallenge(challenge: Challenge) {
        binding.challenge.text = challenge.description
    }

    fun setOnDeleteClickListener(challenge: Challenge) {
        binding.deleteChallengeButton.setOnClickListener {
            listener.onDeleteChallenge(challenge)
        }
    }

    fun setOnEditClickListener(challenge: Challenge) {
        binding.editChallengeButton.setOnClickListener {
            listener.onEditChallenge(challenge)
        }
    }
}