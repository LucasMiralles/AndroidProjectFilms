package com.example.firstapplication

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter


@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FilmScreen(navController: NavController) {
    val mainViewModel: MainViewModel = viewModel()

    Scaffold(
        topBar = {
            TopNavBar(navController)
        },
        bottomBar = {
            BottomNavBar(navController)
        }
    ) {
        val modifier = Modifier.padding(top = 60.dp, bottom = 60.dp)
        Films(navController, mainViewModel, modifier = modifier)
    }
}


@Composable
fun Films(
    navController: NavController, viewModel: MainViewModel, modifier: Modifier
) {

    val movies by viewModel.movies.collectAsState()

    if (movies.isEmpty()) {
        viewModel.getTrendingMovies()
    }

    if (movies.isNotEmpty()) {
        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = modifier) {
            items(movies) { movie ->
                /*  FloatingActionButton(
                      onClick = {/*navController.navigate("DetailMovie/${movie.id}")*/ },
                      modifier = Modifier
                          .width(350.dp)
                          .height(350.dp)
                          .padding(20.dp),
                      containerColor = Color.White,
                  )*/ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                modifier = Modifier
                    .fillMaxWidth() // Utilisez toute la largeur de la colonne
                    .padding(10.dp) // Ajoutez un espace autour de chaque carte
                    .height(350.dp) // Définissez la hauteur de la carte
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp) // Ajoutez un espace à l'intérieur de la carte
                ) {
                    // Image du film
                    Image(
                        painter = rememberImagePainter(
                            data = "https://image.tmdb.org/t/p/w780" + movie.poster_path,
                            builder = {
                                crossfade(true)
                                size(350, 400)
                            }
                        ),
                        contentDescription = "Image film ${movie.title}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp) // Ajustez la hauteur de l'image
                    )

                    // Titre du film
                    Text(
                        text = movie.title,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .fillMaxWidth()
                    )
                }
            }
            }
        }
    }
}



