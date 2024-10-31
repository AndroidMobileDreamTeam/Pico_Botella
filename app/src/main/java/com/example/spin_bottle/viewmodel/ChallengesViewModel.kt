package com.example.spin_bottle.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.spin_bottle.model.Challenge
import com.example.spin_bottle.repository.ChallengeRepository
import kotlinx.coroutines.launch

class ChallengesViewModel(application: Application) : AndroidViewModel(application) {
    val context = getApplication<Application>()
    private val challengeRepository = ChallengeRepository(context)

    private val _challengesList = MutableLiveData<MutableList<Challenge>>()
    val challengesList: LiveData<MutableList<Challenge>> get() = _challengesList

    private val _progressState = MutableLiveData(false)
    val progressState: LiveData<Boolean> = _progressState

    fun saveChallenge(challenge: Challenge) {
        viewModelScope.launch {
            _progressState.value = true
            try {
                challengeRepository.saveChallenge(challenge)
                getChallengesList()
                _progressState.value = false
            } catch (e: Exception) {
                _progressState.value = false
            }
        }
    }

    fun getChallengesList() {
        viewModelScope.launch {
            _progressState.value = true
            try {
                _challengesList.value = challengeRepository.getChallengesList()
                _progressState.value = false
            } catch (e: Exception) {
                _progressState.value = false
            }
        }
    }

    fun deleteChallenge(challenge: Challenge) {
        viewModelScope.launch {
            _progressState.value = true
            try {
                challengeRepository.deleteChallenge(challenge)
                getChallengesList()
                _progressState.value = false
            } catch (e: Exception) {
                _progressState.value = false
            }

        }
    }

    fun updateChallenge(challenge: Challenge) {
        viewModelScope.launch {
            _progressState.value = true
            try {
                challengeRepository.updateChallenge(challenge)
                getChallengesList()
                _progressState.value = false
            } catch (e: Exception) {
                _progressState.value = false
            }
        }
    }
}