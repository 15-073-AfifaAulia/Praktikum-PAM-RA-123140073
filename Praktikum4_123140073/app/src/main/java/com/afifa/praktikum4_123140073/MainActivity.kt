package com.afifa.praktikum4_123140073

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.afifa.praktikum4_123140073.ui.ProfileScreen
import com.afifa.praktikum4_123140073.ui.theme.Praktikum4_123140073Theme
import com.afifa.praktikum4_123140073.viewmodel.ProfileViewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<ProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val uiState by viewModel.uiState.collectAsState()

            Praktikum4_123140073Theme(darkTheme = uiState.isDarkMode) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {

                        ProfileScreen(
                            uiState = uiState,
                            onSaveProfile = { newName, newMajor, newNim ->
                                viewModel.updateProfile(newName, newMajor, newNim)
                            },
                            onToggleTheme = {
                                viewModel.toggleDarkMode()
                            }
                        )

                    }
                }
            }
        }
    }
}