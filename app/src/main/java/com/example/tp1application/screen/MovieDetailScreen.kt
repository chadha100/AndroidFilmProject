import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.tp1application.data.viewmodel.MovieViewModel
import com.example.tp1application.model.Actor

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
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE

    // Ajoutez `verticalScroll` ici pour activer le défilement
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()), // Activation du défilement
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (movieDetails != null) {
            val movie = movieDetails!!

            // Affichage du film en mode portrait ou paysage
            if (isLandscape) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val imageUrl = "https://image.tmdb.org/t/p/w500${movie.poster_path}"
                    Image(
                        painter = rememberImagePainter(imageUrl),
                        contentDescription = movie.title,
                        modifier = Modifier
                            .size(180.dp)
                            .clip(MaterialTheme.shapes.medium)
                            .padding(8.dp)
                    )

                    // Informations sur le film
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = movie.title ?: "Unknown Title",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                color = Color.White,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Text(
                            text = "Release Date: ${movie.release_date ?: "Unknown Date"}",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color.LightGray,
                                fontSize = 16.sp
                            ),
                            modifier = Modifier.padding(bottom = 12.dp)
                        )

                        Text(
                            text = movie.overview ?: "No description available.",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = Color.White,
                                fontSize = 18.sp
                            ),
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                }
            } else {
                // Mode portrait
                val imageUrl = "https://image.tmdb.org/t/p/w500${movie.poster_path}"
                Image(
                    painter = rememberImagePainter(imageUrl),
                    contentDescription = movie.title,
                    modifier = Modifier
                        .size(220.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .padding(8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = movie.title ?: "Unknown Title",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "Release Date: ${movie.release_date ?: "Unknown Date"}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.LightGray,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Text(
                    text = movie.overview ?: "No description available.",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.White,
                        fontSize = 18.sp
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            // Liste des acteurs (remplacer "Cast" par "Actors")
            movie.credits?.cast?.let { actors ->
                if (actors.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Actors:",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(actors) { actor ->
                            ActorItem(actor = actor)
                        }
                    }
                }
            }

            OutlinedButton(
                onClick = { /* Action pour plus de détails */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.White
                ),
                border = BorderStroke(1.dp, Color.White),
            ) {
                Text(text = "More Details", style = MaterialTheme.typography.bodyMedium.copy(color = Color.White))
            }
        } else {
            CircularProgressIndicator(
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }

    Log.d("MovieDetailScreen", "Fetching details for movie ID: $movieId")
}

@Composable
fun ActorItem(actor: Actor) {
    val imageUrl = "https://image.tmdb.org/t/p/w500${actor.profile_path}"
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(120.dp)
    ) {
        Image(
            painter = rememberImagePainter(imageUrl),
            contentDescription = actor.name,
            modifier = Modifier
                .size(90.dp)  // Image un peu plus petite pour optimiser l'espace
                .clip(MaterialTheme.shapes.medium)
                .padding(8.dp)
        )
        Text(
            text = actor.name,
            style = MaterialTheme.typography.bodySmall.copy(color = Color.White),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
