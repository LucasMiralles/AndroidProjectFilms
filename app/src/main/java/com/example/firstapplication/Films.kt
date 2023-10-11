package com.example.firstapplication

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Films(navController: NavController,
          windowClass: WindowSizeClass, viewModel: MainViewModel) {

    val movies by viewModel.movies.collectAsState()

    if (movies.isEmpty()) viewModel.getTrendingMovies()
    LazyColumn{
        items(movies){
                movie -> Text(text = movie.original_title)
        }
    }


    Spacer(modifier = Modifier.width(20.dp))
    Button(onClick = { navController.popBackStack() }) {
        Text(text = "Retour")
    }
}