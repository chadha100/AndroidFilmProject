package com.example.tp1application.screen
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Tv
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
@Composable
fun BottomNavBar(navController: NavController) {
    val mauveColor = Color(0xFF6A0DAD) // Couleur mauve pour le thème
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
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Image, contentDescription = "Collections") },
            label = { Text("Collections") },
            selected = false,
            onClick = { navController.navigate("collections") },
            selectedContentColor = Color.White,
            unselectedContentColor = Color.LightGray
        )
    }
}