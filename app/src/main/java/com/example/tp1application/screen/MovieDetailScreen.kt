import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.tp1application.data.viewmodel.MovieViewModel
@Composable
fun MovieDetailScreen(movieId: String, viewModel: MovieViewModel) {
    // Charger les détails du film
    LaunchedEffect(movieId) {
        viewModel.getMovieDetails(movieId)
    }
    // Observer les détails du film
    val movieDetails by viewModel.movieDetails.collectAsState()

    // Définir le fond mauve
    val backgroundColor = Color(0xFF8A2BE2)  // Couleur mauve

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)  // Appliquer le fond mauve
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (movieDetails != null) {
            val movie = movieDetails!!
            Log.d("MovieDetailScreen", "Movie Details: $movie")

            // Afficher l'affiche du film
            val imageUrl = "https://image.tmdb.org/t/p/w500${movie.poster_path}"
            Image(
                painter = rememberImagePainter(imageUrl),
                contentDescription = movie.title,
                modifier = Modifier
                    .size(200.dp)
                    .padding(8.dp)
            )

            // Titre du film
            Text(
                text = movie.title ?: "Unknown Title",
                style = MaterialTheme.typography.headlineMedium.copy(color = Color.White)
            )

            // Date de sortie
            Text(
                text = "Release Date: ${movie.release_date ?: "Unknown Date"}",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.LightGray)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Description du film
            Text(
                text = movie.overview ?: "No description available.",
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
            )
        } else {
            Text(
                text = "Loading movie details...",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
    Log.d("MovieDetailScreen", "Fetching details for movie ID: $movieId")
}
