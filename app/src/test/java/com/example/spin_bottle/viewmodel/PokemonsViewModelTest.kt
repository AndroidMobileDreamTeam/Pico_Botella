package com.example.spin_bottle.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.spin_bottle.repository.PokemonRepository
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.mockito.Mockito.mock

class PokemonsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var pokemonsViewModel: PokemonsViewModel
    private lateinit var pokemonRepository: PokemonRepository

    @Before
    fun setUp() {
        pokemonRepository = mock(PokemonRepository::class.java)
        pokemonsViewModel = PokemonsViewModel(pokemonRepository)
    }
}