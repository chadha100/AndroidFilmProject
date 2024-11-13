package com.example.tp1application.screen
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.icons.filled.Search
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
    var isSearchVisible by remember { mutableStateOf(false) } // État pour contrôler la visibilité de la barre de recherche
    val mauveColor = Color(0xFF6A0DAD) // Couleur mauve pour correspondre à l'image

    LaunchedEffect(Unit) {
        isLoading = true
        viewModel.getTrendingMovies()
        isLoading = false
    }

    Scaffold(
        bottomBar = { BottomNavBar(navController) },
        topBar = {
            TopAppBar(
                title = { Text("Fav'app", color = Color.White) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = mauveColor // Remplacez backgroundColor par containerColor
                ),
                actions = {
                    IconButton(
                        onClick = {
                            isSearchVisible = !isSearchVisible // Basculer l'état de visibilité de la barre de recherche
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
            // Animation de la barre de recherche en fonction de isSearchVisible
            AnimatedVisibility(visible = isSearchVisible) {
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
                    columns = GridCells.Adaptive(minSize = 160.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(movies.size) { index ->
                        val movie = movies[index]
                        MovieItem(
                            movie = movie,
                            navController = navController
                        )
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
                navController.navigate("movieDetail/${movie.id}")
            }
            .background(Color.White),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(8.dp) // Augmente l'élévation pour un effet plus prononcé
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
                    .size(150.dp) // Augmente la taille de l'image pour une meilleure présentation
                    .padding(8.dp)
            )
            Text(
                text = movie.title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.Black,
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = movie.release_date,
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
