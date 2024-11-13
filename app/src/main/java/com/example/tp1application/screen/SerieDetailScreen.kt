package com.example.tp1application.screen

import android.util.Log
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
import com.example.tp1application.data.viewmodel.SerieViewModel

@Composable
fun SerieDetailScreen(serieId: String, viewModel: SerieViewModel) {
    // Charger les détails de la série
    LaunchedEffect(serieId) {
        viewModel.getSerieDetails(serieId)
    }

    // Observer les détails de la série
    val serieDetails by viewModel.serieDetails.collectAsState()

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
        if (serieDetails.isNotEmpty()) {
            val serie = serieDetails.first() // récupérer la première série de la liste

            // Afficher l'affiche de la série
            val imageUrl = "https://image.tmdb.org/t/p/w500${serie.poster_path}"
            Image(
                painter = rememberImagePainter(imageUrl),
                contentDescription = serie.name,
                modifier = Modifier
                    .size(200.dp)
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Titre de la série
            Text(
                text = serie.name ?: "Unknown Title",
                style = MaterialTheme.typography.headlineMedium.copy(color = Color.White)
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Date de première diffusion
            Text(
                text = "First Air Date: ${serie.first_air_date ?: "Unknown Date"}",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.LightGray)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Description de la série
            Text(
                text = serie.overview ?: "No description available.",
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
            )
        } else {
            Text(
                text = "Loading serie details...",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }

    Log.d("SerieDetailScreen", "Fetching details for serie ID: $serieId")
}