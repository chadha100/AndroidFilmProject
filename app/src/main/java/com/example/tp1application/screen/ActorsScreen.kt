package com.example.tp1application.screen
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.tp1application.data.viewmodel.ActorViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.tp1application.model.ActorModel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActorsScreen(navController: NavHostController, viewModel: ActorViewModel = viewModel()) {
    // Récupère la liste d'acteurs depuis le ViewModel
    val actors by viewModel.actors.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    var isLoading by remember { mutableStateOf(false) }
    var isSearchVisible by remember { mutableStateOf(false) }
    val mauveColor = Color(0xFF6A0DAD) // Couleur mauve pour la cohérence avec l'autre écran

    // Charger les acteurs au démarrage
    LaunchedEffect(Unit) {
        isLoading = true
        viewModel.lastActors()
        isLoading = false
    }

    Scaffold(
        bottomBar = { BottomNavBar(navController) },
        topBar = {
            TopAppBar(
                title = { Text("Actors", color = Color.White) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = mauveColor
                ),
                actions = {
                    IconButton(
                        onClick = {
                            isSearchVisible = !isSearchVisible // Toggle search bar visibility
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Animation de la barre de recherche
            AnimatedVisibility(visible = isSearchVisible) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { query ->
                        viewModel.searchActors(query)
                    },
                    placeholder = { Text(text = "Search Actors...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon",
                            tint = mauveColor
                        )
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = mauveColor,
                        cursorColor = mauveColor,
                        containerColor = Color.White,
                    )
                )
            }

            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 150.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(actors) { actor ->
                        ActorItem(actor = actor, navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun ActorItem(actor: ActorModel, navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                navController.navigate("actorDetail/${actor.id}")
            }
            .background(Color.White),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(8.dp) // Effet d'élévation
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Image de l'acteur
            val imageUrl = "https://image.tmdb.org/t/p/w300${actor.profile_path}"
            Image(
                painter = rememberImagePainter(imageUrl),
                contentDescription = actor.name,
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .padding(8.dp)
            )

            // Nom de l'acteur
            Text(
                text = actor.name,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.Black,
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(top = 8.dp)
            )

            // Département de l'acteur (ex. acteur, réalisateur)
            actor.known_for_department?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}