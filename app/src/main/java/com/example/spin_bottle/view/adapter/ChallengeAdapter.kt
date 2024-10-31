package com.example.spin_bottle.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spin_bottle.model.Challenge
import com.example.spin_bottle.view.viewholder.ChallengeViewHolder
import com.example.spin_bottle_app.databinding.ItemChallengesBinding

class ChallengeAdapter(
    private val challengesList: MutableList<Challenge>,
    private val listener: ChallengeActionListener
) : RecyclerView.Adapter<ChallengeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeViewHolder {
        val binding =
            ItemChallengesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChallengeViewHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return challengesList.size
    }

    override fun onBindViewHolder(holder: ChallengeViewHolder, position: Int) {
        val challenge = challengesList[position]

        holder.setItemChallenge(challenge)
        holder.setOnDeleteClickListener(challenge)
        holder.setOnEditClickListener(challenge)
    }
}