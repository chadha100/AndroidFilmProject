package com.example.tp1application.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tp1application.data.viewmodel.ActorViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import com.example.tp1application.data.viewmodel.MovieViewModel
import com.example.tp1application.model.ActorModel
import androidx.lifecycle.viewmodel.compose.viewModel
@Composable
fun ActorsScreen(viewModel: ActorViewModel = viewModel()) {
    // Récupère la liste d'acteurs depuis le ViewModel
    val actors by viewModel._actors.collectAsState()

    // Charge les acteurs au démarrage de l'écran
    LaunchedEffect(Unit) {
        viewModel.lastActors()
    }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(actors) { actor ->
            ActorItem(actor)
        }
    }
}

@Composable
fun ActorItem(actor: ActorModel) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable { /* Ajoutez une action si nécessaire */ },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Chargement de l'image de l'acteur
        val imageUrl = "https://image.tmdb.org/t/p/w300${actor.profile_path}"
        Image(
            painter = rememberImagePainter(imageUrl),
            contentDescription = actor.name,
            modifier = Modifier
                .size(128.dp)
                .clip(CircleShape)
        )
        // Affichage du nom et du département de l'acteur
        Text(text = actor.name, style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
        Text(text = actor.known_for_department, style = MaterialTheme.typography.bodySmall, textAlign = TextAlign.Center)
        }
}

