package com.example.spin_bottle.repository

import android.content.Context
import com.example.spin_bottle.model.Challenge
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ChallengeRepository(val context: Context) {
    private val firestore = FirebaseFirestore.getInstance()

    private fun getCurrentUserId(): String? {
        return FirebaseAuth.getInstance().currentUser?.uid
    }

    suspend fun getChallengesList(): Result<MutableList<Challenge>> {
        val userId = getCurrentUserId()

        return userId?.let {
            try {
                val snapshot = firestore
                    .collection("users")
                    .document(it)
                    .collection("challenges")
                    .get()
                    .await()

                val challengesList = snapshot.toObjects(Challenge::class.java)
                Result.success(challengesList)
            } catch (e: Exception) {
                Result.failure(Exception("Error fetching challenges: ${e.message}"))
            }
        } ?: Result.failure(Exception("User not authenticated"))
    }

    suspend fun saveChallenge(challenge: Challenge): Result<Unit> {
        val userId = getCurrentUserId()

        return userId?.let {
            try {
                firestore
                    .collection("users")
                    .document(it)
                    .collection("challenges")
                    .add(challenge)
                    .await()

                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(Exception("Error saving challenge: ${e.message}"))
            }
        } ?: Result.failure(Exception("User not authenticated"))
    }

    suspend fun deleteChallenge(challenge: Challenge): Result<Unit> {
        val userId = getCurrentUserId()

        return userId?.let {
            challenge.id?.let { challengeId ->
                try {
                    firestore
                        .collection("users")
                        .document(it)
                        .collection("challenges")
                        .document(challengeId)
                        .delete()
                        .await()

                    Result.success(Unit)
                } catch (e: Exception) {
                    Result.failure(Exception("Error deleting challenge: ${e.message}"))
                }
            } ?: Result.failure(Exception("Challenge ID is null"))
        } ?: Result.failure(Exception("User not authenticated"))
    }

    suspend fun updateChallenge(challenge: Challenge): Result<Unit> {
        val userId = getCurrentUserId()

        return userId?.let {
            challenge.id?.let { challengeId ->
                try {
                    firestore
                        .collection("users")
                        .document(it)
                        .collection("challenges")
                        .document(challengeId)
                        .set(challenge)
                        .await()

                    Result.success(Unit)
                } catch (e: Exception) {
                    Result.failure(Exception("Error updating challenge: ${e.message}"))
                }
            } ?: Result.failure(Exception("Challenge ID is null"))
        } ?: Result.failure(Exception("User not authenticated"))
    }
}