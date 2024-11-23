package com.example.spin_bottle.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spin_bottle.model.Challenge
import com.example.spin_bottle.repository.ChallengeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChallengesViewModel @Inject constructor(
    private val challengeRepository: ChallengeRepository
) : ViewModel() {

    private val _challengesList = MutableLiveData<MutableList<Challenge>>()
    val challengesList: LiveData<MutableList<Challenge>> get() = _challengesList

    private val _progressState = MutableLiveData(false)
    val progressState: LiveData<Boolean> = _progressState

    fun getChallengesList() {
        viewModelScope.launch {
            _progressState.value = true
            try {
                val result = challengeRepository.getChallengesList()

                if (result.isSuccess) {
                    _challengesList.value = result.getOrElse { mutableListOf() }
                } else {
                    _challengesList.value = mutableListOf()
                }
                _progressState.value = false
            } catch (e: Exception) {
                _progressState.value = false
            }
        }
    }

    fun saveChallenge(challenge: Challenge) {
    }

    fun deleteChallenge(challenge: Challenge) {
    }

    fun updateChallenge(challenge: Challenge) {
    }
}