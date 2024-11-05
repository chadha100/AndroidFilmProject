package com.example.tp1application.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.tp1application.R
import com.example.tp1application.data.viewmodel.MovieViewModel
import com.example.tp1application.model.MovieModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(navController: NavHostController, viewModel: MovieViewModel = viewModel()) {
    val movies by viewModel.movies.collectAsState()
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    var isLoading by remember { mutableStateOf(false) }
    val mauveColor = Color(0xFF6A0DAD) // Couleur mauve pour le thème

    LaunchedEffect(Unit) {
        isLoading = true
        viewModel.getTrendingMovies()
        isLoading = false
    }

    Scaffold(
        bottomBar = {
            BottomNavigation(backgroundColor = mauveColor) {
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Movie, contentDescription = "Films") },
                    label = { Text("Films") },
                    selected = false,
                    onClick = { navController.navigate("movies") },
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.LightGray
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Tv, contentDescription = "Séries") },
                    label = { Text("Séries") },
                    selected = false,
                    onClick = { navController.navigate("series") },
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.LightGray
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Acteurs") },
                    label = { Text("Acteurs") },
                    selected = false,
                    onClick = { navController.navigate("actors") },
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.LightGray
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF6200EE))
                    .padding(16.dp)
            ) {
                // Barre de recherche avec icône de loupe
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { query ->
                        searchQuery = query
                        if (query.text.isNotEmpty()) {
                            isLoading = true
                            viewModel.SearchKeyWord(query.text)
                            isLoading = false
                        } else {
                            viewModel.getTrendingMovies()
                        }
                    },
                    placeholder = { Text(text = "Rechercher un film...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    leadingIcon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.baseline_search_24),
                            contentDescription = "Search Icon"
                        )
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF6200EE),
                        cursorColor = Color(0xFF6200EE),
                        containerColor = Color.White, // Fond blanc pour la barre de recherche
                    )
                )

                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                } else {
                    // Grid des films dans des cartes avec fond mauve
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = 160.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(movies.size) { index ->
                            val movie = movies[index]
                            MovieItem(
                                movie = movie,
                                navController = navController
                            )  // Passer le navController
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MovieItem(movie: MovieModel, navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                // Naviguer vers l'écran de détails du film en passant l'ID du film
                navController.navigate("movieDetail/${movie.id}")  // Assurez-vous que 'id' est une propriété de MovieModel
            }
            .background(Color(0xFF6200EE)),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val imageUrl = "https://image.tmdb.org/t/p/w500${movie.poster_path}"
            Image(
                painter = rememberImagePainter(imageUrl),
                contentDescription = movie.title,
                modifier = Modifier
                    .size(140.dp)
                    .padding(8.dp)
            )
            Text(
                text = movie.title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.White,
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = movie.release_date,
                style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFFBDBDBD)),
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
