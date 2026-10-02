package com.afifa.praktikum4_123140073.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.afifa.praktikum4_123140073.data.ProfileUiState

@Composable
fun ProfileScreen(
    uiState: ProfileUiState,
    onSaveProfile: (String, String, String) -> Unit, // Menerima 3 parameter
    onToggleTheme: () -> Unit
) {
    // State lokal untuk form edit
    var tempName by remember { mutableStateOf(uiState.name) }
    var tempMajor by remember { mutableStateOf(uiState.major) }
    var tempNim by remember { mutableStateOf(uiState.nim) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // HEADER & DARK MODE TOGGLE
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "My Profile App", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = if (uiState.isDarkMode) "Dark" else "Light")
                Spacer(modifier = Modifier.width(8.dp))
                Switch(checked = uiState.isDarkMode, onCheckedChange = { onToggleTheme() })
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // TAMPILAN PROFIL (Read-Only)
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = uiState.name, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                // Jurusan
                Text(text = uiState.major, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(4.dp))
                // NIM dibuat lebih kecil dan samar
                Text(text = uiState.nim, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // FORM EDIT
        Text(
            text = "Edit Profile",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(16.dp))

        EditProfileForm(
            currentName = tempName,
            currentMajor = tempMajor,
            currentNim = tempNim,
            onNameChange = { tempName = it },
            onMajorChange = { tempMajor = it },
            onNimChange = { tempNim = it }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { onSaveProfile(tempName, tempMajor, tempNim) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Profile")
        }
    }
}

@Composable
fun EditProfileForm(
    currentName: String,
    currentMajor: String,
    currentNim: String,
    onNameChange: (String) -> Unit,
    onMajorChange: (String) -> Unit,
    onNimChange: (String) -> Unit
) {
    Column {
        OutlinedTextField(
            value = currentName,
            onValueChange = onNameChange,
            label = { Text("Nama") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = currentMajor,
            onValueChange = onMajorChange,
            label = { Text("Program Studi") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = currentNim,
            onValueChange = onNimChange,
            label = { Text("NIM") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}