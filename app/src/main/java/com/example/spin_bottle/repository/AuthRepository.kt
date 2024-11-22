package com.example.spin_bottle.repository

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()

    suspend fun registerUser(email: String, password: String): Result<String> {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            Result.success(email)
        } catch (e: Exception) {
            Result.failure(Exception("Error en el registro", e))
        }
    }

    suspend fun loginUser(email: String, password: String): Result<String> {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Result.success(email)
        } catch (e: Exception) {
            Result.failure(Exception("Login incorrecto", e))
        }
    }

    fun logout() {
        firebaseAuth.signOut()
    }
}
