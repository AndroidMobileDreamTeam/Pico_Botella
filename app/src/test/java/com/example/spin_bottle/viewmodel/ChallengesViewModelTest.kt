package com.example.spin_bottle.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.spin_bottle.model.Challenge
import com.example.spin_bottle.repository.ChallengeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

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

    @Test
    fun `test getChallengesList`() = runBlocking {
        //Given
        Dispatchers.setMain(UnconfinedTestDispatcher())

        val mockChallenges = mutableListOf(Challenge("1", "Test Challenge"))
        `when`(challengesRepository.getChallengesList()).thenReturn(Result.success(mockChallenges))

        //When
        challengesViewModel.getChallengesList()

        //Then
        assertEquals(challengesViewModel.challengesList.value, mockChallenges)
    }

    @Test
    fun `test saveChallenge`(): Unit = runBlocking {
        //Given
        Dispatchers.setMain(UnconfinedTestDispatcher())

        val challenge = Challenge("1", "Test Challenge")

        //When
        challengesViewModel.saveChallenge(challenge)

        //Then
        verify(challengesRepository).saveChallenge(challenge)
    }
}