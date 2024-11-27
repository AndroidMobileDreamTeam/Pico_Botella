package com.example.spin_bottle.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule

class AudioViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var audioViewModel: AudioViewModel

    @Before
    fun setUp() {
        audioViewModel = AudioViewModel()
    }
}