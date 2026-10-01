package com.afifa.praktikum3_123140073

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.afifa.praktikum3_123140073.ui.theme.Praktikum3_123140073Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Praktikum3_123140073Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProfileScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// ==================== COMPOSABLE 1: ProfileHeader ====================
@Composable
fun ProfileHeader(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Frame Foto Profil yang lebih elegan
        Box(
            modifier = Modifier
                .size(110.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .border(3.dp, MaterialTheme.colorScheme.primary, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Foto Profil",
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.size(65.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = name,
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

// ==================== COMPOSABLE 2: InfoItem ====================
@Composable
fun InfoItem(icon: ImageVector, text: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.Medium
        )
    }
}

// ==================== COMPOSABLE 3: ProfileCard ====================
@Composable
fun ProfileCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(20.dp), // Sudut yang lebih membulat kekinian
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            InfoItem(icon = Icons.Default.Email, text = "afifaaulia@email.com")
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))
            InfoItem(icon = Icons.Default.Phone, text = "+62 812-3456-7890")
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f))
            InfoItem(icon = Icons.Default.LocationOn, text = "Bandar Lampung, Indonesia")
        }
    }
}

// ==================== MAIN SCREEN ====================
@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    var showDetails by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        ProfileHeader(name = "Afifa Aulia")

        Spacer(modifier = Modifier.height(8.dp))

        // Sub-judul Program Studi
        Text(
            text = "Mahasiswa Teknik Informatika",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(4.dp))

        // NIM di bawah Program Studi
        Text(
            text = "NIM: 123140073",
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Tombol
        Button(
            onClick = { showDetails = !showDetails },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(50.dp)
        ) {
            Text(
                text = if (showDetails) "Sembunyikan Detail" else "Tampilkan Detail Profil",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Animasi transisi
        AnimatedVisibility(
            visible = showDetails,
            enter = fadeIn(tween(500)) + slideInVertically(tween(500)) { it / 4 },
            exit = fadeOut(tween(300)) + slideOutVertically(tween(300)) { it / 4 }
        ) {
            ProfileCard()
        }
    }
}

// ==================== PREVIEW ====================
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    Praktikum3_123140073Theme {
        ProfileScreen()
    }
}