package com.example.tp1application.screen
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
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
import com.example.tp1application.data.viewmodel.ActorViewModel
@Composable
fun ActorDetailScreen(actorId: String, viewModel: ActorViewModel) {
    // Charger les détails de l'acteur
    LaunchedEffect(actorId) {
        viewModel.getActorDetails(actorId)
    }

    // Observer les détails de l'acteur
    val actorDetails by viewModel.actorDetails.collectAsState(initial = null)

    // Définir le fond mauve
    val backgroundColor = Color(0xFF8A2BE2)

    // Activer le défilement vertical
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),  // Ajout pour le défilement vertical
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (actorDetails != null) {
            val actor = actorDetails!!

            // Afficher l'image de profil de l'acteur avec bordure et ombre
            val imageUrl = "https://image.tmdb.org/t/p/w500${actor.profile_path}"
            Box(
                modifier = Modifier
                    .size(180.dp)
                    .padding(8.dp)
                    .border(
                        width = 2.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .background(
                        Color.Black.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                Image(
                    painter = rememberImagePainter(imageUrl),
                    contentDescription = actor.name,
                    modifier = Modifier.fillMaxSize(),
                    alignment = Alignment.Center,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Nom de l'acteur
            Text(
                text = actor.name ?: "Nom inconnu",
                style = MaterialTheme.typography.headlineLarge.copy(color = Color.White),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Lieu de naissance
            Text(
                text = "Lieu de naissance : ${actor.place_of_birth ?: "Inconnu"}",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.LightGray),
                modifier = Modifier.padding(vertical = 4.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Biographie de l'acteur
            Text(
                text = actor.biography ?: "Aucune biographie disponible.",
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.White),
                modifier = Modifier.padding(horizontal = 16.dp)
            )

        } else {
            Text(
                text = "Chargement des détails de l'acteur...",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }
    }

    Log.d("ActorDetailScreen", "Fetching details for actor ID: $actorId")
}