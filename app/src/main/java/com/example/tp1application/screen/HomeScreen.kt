package com.example.tp1application.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tp1application.R
import com.example.tp1application.data.viewmodel.MovieViewModel

@Composable
fun HomeScreen(navController: NavController, movieViewModel: MovieViewModel = viewModel()) {
    val mauveColor = Color(0xFF6A0DAD) // Couleur mauve pour le thème

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
            // Image et informations de profil
            Image(
                painter = painterResource(id = R.drawable.chadha),
                contentDescription = "Mon image",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(150.dp)
                    .border(BorderStroke(2.dp, Color.DarkGray), CircleShape)
                    .padding(2.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                text = "chadha said",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                "Étudiant en 4ème année cycle d'ingénieur en informatique de santé 2023/2025",
                Modifier.width(350.dp),
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Informations de contact
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Rounded.Call, contentDescription = stringResource(id = R.string.call_content_desc), Modifier.padding(3.dp))
                Text("https://www.google.com/intl/fr/gmail", textAlign = TextAlign.Center, fontStyle = FontStyle.Italic)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Rounded.Email, contentDescription = stringResource(id = R.string.attach_email_content_desc), Modifier.padding(3.dp))
                Text("https://www.google.com/intl/fr/gmail", textAlign = TextAlign.Center, fontStyle = FontStyle.Italic)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Bouton "Démarrer"
            Button(onClick = { navController.navigate("movies") }) {
                Text(text = "Démarrer")
            }
        }
    }
}
