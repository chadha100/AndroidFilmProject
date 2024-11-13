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
import com.example.premiere_application.HomeScreen
import com.example.tp1application.data.viewmodel.ActorViewModel
import com.example.tp1application.data.viewmodel.MovieViewModel
import com.example.tp1application.data.viewmodel.SerieViewModel
import com.example.tp1application.screen.*
@Composable
fun RootNavigation(navController: NavHostController, windowClass: WindowSizeClass) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("home") { HomeScreen(windowClass,navController) }
        composable("movies") {
            val movieViewModel: MovieViewModel = viewModel()
            MovieScreen(
                navController = navController,
                viewModel = movieViewModel
            ) // Passer le navController
        }
        composable("actors") {
            val actorViewModel: ActorViewModel = viewModel()
            ActorsScreen(navController = navController, actorViewModel)
        }
        composable("series") {
            val serieViewModel: SerieViewModel = viewModel()
            SeriesScreen(navController = navController, serieViewModel)
        }
        // Nouvelle route pour l'écran de détails des films
        composable("movieDetail/{movieId}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId") ?: ""
            val movieViewModel: MovieViewModel = viewModel()  // Création du ViewModel sans Hilt
            MovieDetailScreen(
                movieId,
                viewModel = movieViewModel
            )  // Passer le ViewModel en paramètre
        }
        // Route pour les détails d'une série, acceptant le serieId comme paramètre
        composable("serieDetail/{serieId}") { backStackEntry ->
            val serieId = backStackEntry.arguments?.getString("serieId") ?: ""
            val serieViewModel: SerieViewModel = viewModel()  // Créer le ViewModel sans Hilt si nécessaire
            SerieDetailScreen(
                serieId = serieId,
                viewModel = serieViewModel
            )
        }
// Route pour les détails d'un acteur, acceptant le actorId comme paramètre
        composable("actorDetail/{actorId}") { backStackEntry ->
            val actorId = backStackEntry.arguments?.getString("actorId") ?: ""
            val actorViewModel: ActorViewModel = viewModel()  // Créer le ViewModel sans Hilt si nécessaire
            ActorDetailScreen(
                actorId = actorId,
                viewModel = actorViewModel
            )
        }
    }
}

