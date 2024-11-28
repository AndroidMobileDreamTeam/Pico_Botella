package com.example.spin_bottle.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.spin_bottle.model.Challenge
import com.example.spin_bottle.model.Pokemon
import com.example.spin_bottle.repository.PokemonRepository
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

    @Test
    fun `test getPokemons`() = runBlocking {
        //Given
        Dispatchers.setMain(UnconfinedTestDispatcher())

        val mockPokemons = mutableListOf(Pokemon(1, "https://Pokemon1"))
        `when`(pokemonRepository.getPokemons()).thenReturn(mockPokemons)

        //When
        pokemonsViewModel.getPokemons()

        //Then
        assertEquals(pokemonsViewModel.pokemonsList.value, mockPokemons)
    }
}