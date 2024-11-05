package com.example.tp1application.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tp1application.data.viewmodel.SerieViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.tp1application.model.SerieModel

@Composable
fun SeriesScreen(viewModel: SerieViewModel = viewModel()) {
    // Récupère la liste de séries depuis le ViewModel
    val series by viewModel.series.collectAsState()

    // Charge les séries au démarrage de l'écran
    LaunchedEffect(Unit) {
        viewModel.lastSeries()
    }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(series) { serie ->
            SerieItem(serie)
        }
    }
}

@Composable
fun SerieItem(serie: SerieModel) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable { /* Ajoutez une action si nécessaire */ },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Chargement de l'image de la série
        val imageUrl = "https://image.tmdb.org/t/p/w300${serie.poster_path}"
        Image(
            painter = rememberImagePainter(imageUrl),
            contentDescription = serie.name,
            modifier = Modifier
                .size(128.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        // Affichage du nom et de la date de première diffusion de la série
        Text(
            text = serie.name,
            style = MaterialTheme.typography.h6, // Remplacez par un style disponible
            textAlign = TextAlign.Center
        )
        Text(
            text = "Première diffusion: ${serie.first_air_date}",
            style = MaterialTheme.typography.body2, // Remplacez également ici
            textAlign = TextAlign.Center
        )
    }
}
