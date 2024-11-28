package com.example.spin_bottle.viewmodel

import android.content.Context
import android.media.MediaPlayer
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.spin_bottle_app.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockedStatic
import org.mockito.Mockito.mock
import org.mockito.Mockito.mockStatic
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class AudioViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var audioViewModel: AudioViewModel
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var mediaPlayerStatic: MockedStatic<MediaPlayer>
    private lateinit var context: Context

    @Before
    fun setUp() {
        mediaPlayer = mock(MediaPlayer::class.java)
        mediaPlayerStatic = mockStatic(MediaPlayer::class.java)
        context = mock(Context::class.java)
        audioViewModel = AudioViewModel()
    }

    @After
    fun tearDown() {
        mediaPlayerStatic.close()
    }

    @Test
    fun `test initMediaPlayer`() {
        //Given
        `when`(MediaPlayer.create(context, R.raw.background_music)).thenReturn(mediaPlayer)

        //When
        audioViewModel.initMediaPlayer(context)

        //Then
        assertNotNull(audioViewModel.mediaPlayer)
    }

    @Test
    fun `test toggleAudio`() = runBlocking {
        //Given
        Dispatchers.setMain(UnconfinedTestDispatcher())

        val initAudioState = audioViewModel.isAudioOn.value
        val expectedAudioState = !(audioViewModel.isAudioOn.value ?: true)

        //When
        audioViewModel.toggleAudio()

        //Then
        assertEquals(audioViewModel.isAudioOn.value, expectedAudioState)
    }

    @Test
    fun `test spinSound`() {
        //Given
        `when`(MediaPlayer.create(context, R.raw.spin_bottle)).thenReturn(mediaPlayer)

        //When
        audioViewModel.spinSound(context)

        //Then
        verify(mediaPlayer).start()
    }

    @Test
    fun `test homeSound`() = runBlocking {
        //Given
        Dispatchers.setMain(UnconfinedTestDispatcher())

        `when`(MediaPlayer.create(context, R.raw.background_music)).thenReturn(mediaPlayer)

        //When
        audioViewModel.homeSound(context)

        //Then
        verify(mediaPlayer).start()
    }

    @Test
    fun `test releaseMediaPlayer`() {
        //Given
        `when`(MediaPlayer.create(context, R.raw.background_music)).thenReturn(mediaPlayer)

        //When
        audioViewModel.initMediaPlayer(context)
        audioViewModel.releaseMediaPlayer()

        //Then
        verify(mediaPlayer).release()
        assertNull(audioViewModel.mediaPlayer)
    }
}