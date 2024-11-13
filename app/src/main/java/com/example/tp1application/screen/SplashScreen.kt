package com.example.tp1application.screen
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tp1application.R
import kotlinx.coroutines.delay
@Composable
fun SplashScreen(navController: NavController) {
    val mauveColor = Color(0xFF6A0DAD) // Couleur de fond mauve
    val transitionDuration = 2000 // Durée de l'animation en millisecondes

    // Utiliser un LaunchedEffect pour naviguer après un délai
    LaunchedEffect(Unit) {
        delay(2000) // Délai de 2 secondes
        navController.navigate("home") // Naviguer vers l'écran principal
    }

    // Animation de translation
    var offset by remember { mutableStateOf(0f) }
    val animatedOffset by animateFloatAsState(
        targetValue = if (offset == 0f) 100f else 0f,
        animationSpec = tween(durationMillis = transitionDuration, easing = FastOutSlowInEasing)
    )

    // Animation de l'échelle du texte
    val scale by animateFloatAsState(
        targetValue = if (offset == 0f) 1.2f else 1f,
        animationSpec = tween(durationMillis = transitionDuration, easing = FastOutSlowInEasing)
    )

    // Utiliser un LaunchedEffect pour faire osciller l'image
    LaunchedEffect(Unit) {
        while (true) {
            offset = if (offset == 0f) 100f else 0f
            delay(transitionDuration.toLong())
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(mauveColor),
        contentAlignment = Alignment.Center
    ) {
        // Utiliser une Column pour empiler l'image et le texte
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.delicious_popcorn_box_ready_be_served), // Remplacez par votre image
                contentDescription = "Logo",
                modifier = Modifier
                    .size(150.dp)
                    .offset(x = animatedOffset.dp) // Appliquer le décalage animé
            )
            Spacer(modifier = Modifier.height(16.dp)) // Ajouter un espacement entre l'image et le texte
            Text(
                text = "Fav'App", // Changer le texte ici
                style = MaterialTheme.typography.titleLarge, // Choisir un style de texte approprié
                color = Color.White, // Couleur du texte
                modifier = Modifier.scale(scale) // Appliquer l'animation d'échelle au texte
            )
        }
    }
}