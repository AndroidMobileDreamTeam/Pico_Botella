package com.example.spin_bottle.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.spin_bottle.repository.AuthRepository
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.mockito.Mockito.mock

class AuthViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var authViewModel: AuthViewModel
    private lateinit var authRepository: AuthRepository

    @Before
    fun setUp() {
        authRepository = mock(AuthRepository::class.java)
        authViewModel = AuthViewModel(authRepository)
    }
}