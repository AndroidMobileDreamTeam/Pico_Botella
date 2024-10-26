package com.example.spin_bottle.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.spin_bottle_app.databinding.ItemChallengesBinding
import com.example.spin_bottle.model.Challenge
import com.example.spin_bottle.view.viewholder.ChallengeViewHolder

class ChallengeAdapter(private val challengesList:MutableList<Challenge>, private val navController: NavController):RecyclerView.Adapter<ChallengeViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeViewHolder {
        val binding = ItemChallengesBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ChallengeViewHolder(binding, navController)
    }

    override fun getItemCount(): Int {
        return challengesList.size
    }

    override fun onBindViewHolder(holder: ChallengeViewHolder, position: Int) {
        val challenge = challengesList[position]
        holder.setItemChallenge(challenge)
    }


}