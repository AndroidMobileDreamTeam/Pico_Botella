package com.example.spin_bottle.model

sealed class AuthStatus {
    data class Success(val message: String) : AuthStatus()
    data class Error(val message: String) : AuthStatus()
}