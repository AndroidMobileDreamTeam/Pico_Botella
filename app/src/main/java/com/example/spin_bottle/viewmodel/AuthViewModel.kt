package com.example.spin_bottle.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spin_bottle.model.AuthStatus
import com.example.spin_bottle.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _loginStatus = MutableLiveData<AuthStatus>()
    val loginStatus: LiveData<AuthStatus> get() = _loginStatus

    private val _registerStatus = MutableLiveData<AuthStatus>()
    val registerStatus: LiveData<AuthStatus> get() = _registerStatus

    fun registerUser(email: String, password: String) {
        viewModelScope.launch {
            val result = authRepository.registerUser(email, password)
            if (result.isSuccess) {
                _registerStatus.postValue(AuthStatus.Success("Registro exitoso"))
            } else {
                _registerStatus.postValue(
                    AuthStatus.Error(
                        result.exceptionOrNull()?.message ?: "Error desconocido"
                    )
                )
            }
        }
    }

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            val result = authRepository.loginUser(email, password)
            if (result.isSuccess) {
                _loginStatus.postValue(AuthStatus.Success("Login Exitoso"))
            } else {
                _loginStatus.postValue(
                    AuthStatus.Error(
                        result.exceptionOrNull()?.message ?: "Error desconocido"
                    )
                )

            }
        }
    }
}
