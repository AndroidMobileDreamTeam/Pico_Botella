package com.example.spin_bottle.viewmodel

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.spin_bottle.model.AuthStatus
import com.example.spin_bottle.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

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

    @Test
    fun `test registerUser`() = runBlocking {
        //Given
        Dispatchers.setMain(UnconfinedTestDispatcher())

        val mockEmail = "example@test.com"
        val password = "test"
        `when`(authRepository.registerUser(mockEmail, password)).thenReturn(Result.success(mockEmail))
        val expectedResult = AuthStatus.Success("Registro exitoso")

        //When
        authViewModel.registerUser(mockEmail, password)

        //Then
        assertEquals(authViewModel.registerStatus.value, expectedResult)
    }

    @Test
    fun `test loginUser`() = runBlocking {
        //Given
        Dispatchers.setMain(UnconfinedTestDispatcher())

        val mockEmail = "example@test.com"
        val password = "test"
        `when`(authRepository.loginUser(mockEmail, password)).thenReturn(Result.success(mockEmail))
        val expectedResult = AuthStatus.Success("Login Exitoso")

        //When
        authViewModel.loginUser(mockEmail, password)

        //Then
        assertEquals(authViewModel.loginStatus.value, expectedResult)
    }
}