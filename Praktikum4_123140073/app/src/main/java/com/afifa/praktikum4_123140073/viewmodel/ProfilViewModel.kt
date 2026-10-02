package com.afifa.praktikum4_123140073.viewmodel

import androidx.lifecycle.ViewModel
import com.afifa.praktikum4_123140073.data.ProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    // fungsi ini menerima parameter name, major, dan nim
    fun updateProfile(newName: String, newMajor: String, newNim: String) {
        _uiState.update { currentState ->
            currentState.copy(
                name = newName,
                major = newMajor,
                nim = newNim
            )
        }
    }

    fun toggleDarkMode() {
        _uiState.update { currentState ->
            currentState.copy(
                isDarkMode = !currentState.isDarkMode
            )
        }
    }
}