package com.example.premiere_application
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tp1application.R
@Composable
fun HomeScreen(windowClass: WindowSizeClass, navController: NavController) {
    val classHauteur = windowClass.heightSizeClass
    when (classHauteur) {
        WindowHeightSizeClass.Medium -> {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painterResource(id = R.drawable.chadha),
                            contentDescription = "Photo Chadha",
                            modifier = Modifier
                                .size(300.dp)
                                .padding(20.dp)
                                .clip(CircleShape)
                        )
                        Text(
                            text = "Chadha Said",
                            style = MaterialTheme.typography.headlineLarge,
                        )
                        Text(
                            text = "Etudiant à l'école d'ingénieur ISIS - INU Champollion",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Row {
                            Icon(
                                imageVector = Icons.Default.Mail,
                                contentDescription = "Icone mail",
                                modifier = Modifier.size(25.dp).padding(bottom = 5.dp, end = 5.dp)
                            )
                            Text(
                                text = "said.mohamedchadha@gmail.com",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                        Row {
                            Icon(
                                imageVector = Icons.Default.AlternateEmail,
                                contentDescription = "Icone LinkedIn",
                                tint = Color(0xFF0077B5), // Couleur LinkedIn
                                modifier = Modifier.size(28.dp).padding(end = 5.dp) // Taille légèrement plus grande
                            )
                            Text(
                                text = "www.linkedin.com/in/chadha-said-b22a36292",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Button(
                            onClick = { navController.navigate("movies") },
                            colors = ButtonDefaults.buttonColors(Color(0xFF8A2BE2)) // Couleur mauve pour le bouton
                        ) {
                            Text("Démarrer")
                        }
                    }
                }
            }
        }
        WindowHeightSizeClass.Compact -> {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painterResource(id = R.drawable.chadha),
                                contentDescription = "Photo Chadha",
                                modifier = Modifier
                                    .size(200.dp)
                                    .clip(CircleShape)
                            )

                            Column {
                                Row {
                                    Icon(
                                        imageVector = Icons.Default.Mail,
                                        contentDescription = "Icone mail",
                                        modifier = Modifier.padding(start = 50.dp)
                                    )
                                    Text(
                                        text = "saidchadha@gmail.com",
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier
                                            .padding(start = 5.dp, top = 1.dp)
                                    )
                                }
                                Row {
                                    Icon(
                                        imageVector = Icons.Default.AlternateEmail,
                                        contentDescription = "Icone LinkedIn",
                                        tint = Color(0xFF0077B5), // Couleur LinkedIn
                                        modifier = Modifier.size(28.dp).padding(start = 50.dp) // Taille légèrement plus grande
                                    )
                                    Text(
                                        text = "www.linkedin.com/in/chadha-said-8453bb244",
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(start = 5.dp, top = 2.dp)
                                    )
                                }
                            }
                        }
                        Row {
                            Column {
                                Text(
                                    text = "Chadha Said",
                                    style = MaterialTheme.typography.headlineSmall,
                                    modifier = Modifier.padding(end = 320.dp)
                                )
                                Text(
                                    text = "Etudiante à l'école d'ingénieur ISIS",
                                    style = MaterialTheme.typography.bodySmall,
                                )
                            }
                            Button(
                                onClick = { navController.navigate("movies") },
                                colors = ButtonDefaults.buttonColors(Color(0xFF8A2BE2)) // Couleur mauve pour le bouton
                            ) {
                                Text(text = "Démarrer")
                            }
                        }
                    }
                }
            }
        }
    }
}
