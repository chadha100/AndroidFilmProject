package com.example.tp1application.screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.tp1application.data.viewmodel.SerieViewModel
import com.example.tp1application.model.SerieModel
import androidx.lifecycle.viewmodel.compose.viewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun SeriesScreen(navController: NavController, viewModel: SerieViewModel = viewModel()) {
    val series by viewModel.series.collectAsState() // Liste des séries par défaut
    val serieDetails by viewModel.serieDetails.collectAsState() // Liste des séries filtrées par recherche
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.lastSeries() // Charger les séries par défaut au début
    }

    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Barre de recherche
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { query ->
                    searchQuery = query
                    if (query.isNotEmpty()) {
                        viewModel.SearchKeyWordS(query) // Effectuer la recherche
                    } else {
                        // Si la recherche est vide, recharger les séries par défaut
                        viewModel.lastSeries()
                    }
                },
                label = { Text("Recherche") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Affichage des séries sous forme de grille, triées par date
            val seriesToDisplay = (if (searchQuery.isEmpty()) series else serieDetails)
                .sortedByDescending { serie ->
                    // Convertir `first_air_date` en LocalDate pour le tri
                    serie.first_air_date?.let {
                        LocalDate.parse(it, DateTimeFormatter.ISO_DATE)
                    }
                }

            if (seriesToDisplay.isEmpty()) {
                Text("Aucune série trouvée.", modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center)
            } else {
                // Affichage de la grille de séries triées
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 150.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(seriesToDisplay) { serie ->
                        SerieItem(serie, navController)
                    }
                }
            }
        }
    }
}

@Composable
fun SerieItem(serie: SerieModel, navController: NavController) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                navController.navigate("serieDetail/${serie.id}")
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val imageUrl = "https://image.tmdb.org/t/p/w300${serie.poster_path}"
        Image(
            painter = rememberImagePainter(imageUrl),
            contentDescription = serie.name,
            modifier = Modifier
                .size(128.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Text(
            text = serie.name,
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Première diffusion: ${serie.first_air_date}",
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center
        )
    }
}

