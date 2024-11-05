package com.example.tp1application.screen
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
@Composable
fun SearchScreen(navController: NavController, query: String) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Résultats pour \"$query\"") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Retour")
                    }
                }
            )
        },
        content = { paddingValues ->
            SearchResultsList(query = query, modifier = Modifier.padding(paddingValues))
        }
    )
}

@Composable
fun SearchResultsList(query: String, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(20) { index ->
            SearchResultItem(result = "Résultat #$index pour \"$query\"")
            Divider()
        }
    }
}

@Composable
fun SearchResultItem(result: String) {
    Text(
        text = result,
        style = MaterialTheme.typography.body1,
        modifier = Modifier.padding(8.dp)
    )
}
