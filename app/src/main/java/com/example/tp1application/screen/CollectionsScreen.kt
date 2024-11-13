package com.example.tp1application.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.tp1application.data.viewmodel.CollectionViewModel
import com.example.tp1application.data.viewmodel.SerieViewModel

@Composable
fun CollectionsScreen( viewModel: CollectionViewModel) {

    LaunchedEffect(Unit) {
        viewModel.getLastColletions()

    }
    val collections by viewModel.collections.collectAsState()

    // Définir le fond mauve
    val backgroundColor = Color(0xFF8A2BE2)  // Couleur mauve

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),  // Activer le défilement vertical
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (collections.isNotEmpty()) {
            val collection = collections.first() // récupérer la première série de la liste
//titre
            Text(
                text = collection.name ?: "Unknown Title",
                style = MaterialTheme.typography.headlineMedium.copy(color = Color.White)
            )

            Spacer(modifier = Modifier.height(4.dp))


        }
    }
}