package com.example.spin_bottle.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.spin_bottle.repository.ChallengeRepository
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.mockito.Mockito.mock

class ChallengesViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var challengesViewModel: ChallengesViewModel
    private lateinit var challengesRepository: ChallengeRepository

    @Before
    fun setUp() {
        challengesRepository = mock(ChallengeRepository::class.java)
        challengesViewModel = ChallengesViewModel(challengesRepository)
    }
}