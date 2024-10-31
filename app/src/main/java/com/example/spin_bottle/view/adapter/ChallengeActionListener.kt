package com.example.spin_bottle.view.adapter

import com.example.spin_bottle.model.Challenge

interface ChallengeActionListener {
    fun onCreateChallenge()
    fun onEditChallenge(challenge: Challenge)
    fun onDeleteChallenge(challenge: Challenge)
}