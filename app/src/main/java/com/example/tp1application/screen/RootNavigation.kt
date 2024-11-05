package com.example.tp1application.screen
// RootNavigation.kt
import MovieDetailScreen
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tp1application.data.viewmodel.ActorViewModel
import com.example.tp1application.data.viewmodel.MovieViewModel
import com.example.tp1application.data.viewmodel.SerieViewModel
import com.example.tp1application.screen.*
@Composable
fun RootNavigation(navController: NavHostController, windowClass: WindowSizeClass) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("movies") {
            val movieViewModel: MovieViewModel = viewModel()
            MovieScreen(navController = navController, viewModel = movieViewModel) // Passer le navController
        }
        composable("actors") {
            val actorViewModel: ActorViewModel = viewModel()
            ActorsScreen(actorViewModel)
        }
        composable("series") {
            val serieViewModel: SerieViewModel = viewModel()
            SeriesScreen(serieViewModel)
        }
        // Nouvelle route pour l'écran de détails des films
        composable("movieDetail/{movieId}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId") ?: ""
            val movieViewModel: MovieViewModel = viewModel()  // Création du ViewModel sans Hilt
            MovieDetailScreen(movieId, viewModel = movieViewModel)  // Passer le ViewModel en paramètre
        }

        composable("search/{query}") { backStackEntry ->
            val query = backStackEntry.arguments?.getString("query") ?: ""
            SearchScreen(navController, query)
        }
    }
}

