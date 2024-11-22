package com.example.spin_bottle.viewmodel

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spin_bottle_app.R

class AudioViewModel : ViewModel() {
    private val _isAudioOn = MutableLiveData(true)
    val isAudioOn: LiveData<Boolean> get() = _isAudioOn

    var mediaPlayer: MediaPlayer? = null

    fun initMediaPlayer(context: Context) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, R.raw.background_music)
            mediaPlayer?.isLooping = true
            if (_isAudioOn.value == true) {
                mediaPlayer?.start()
            }
        }
    }

    fun toggleAudio() {
        _isAudioOn.value = !(_isAudioOn.value ?: true)
        if (_isAudioOn.value == true) {
            mediaPlayer?.start()
        } else {
            mediaPlayer?.pause()
        }
    }

    fun spinSound(context: Context) {
        releaseMediaPlayer()
        mediaPlayer = MediaPlayer.create(context, R.raw.spin_bottle)
        mediaPlayer?.start()
    }

    fun homeSound(context: Context) {
        releaseMediaPlayer()
        mediaPlayer = MediaPlayer.create(context, R.raw.background_music)
        if (_isAudioOn.value == true) {
            mediaPlayer?.start()
        }
    }

    fun releaseMediaPlayer() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}