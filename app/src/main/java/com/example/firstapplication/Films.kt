package com.example.firstapplication

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Surface
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import java.util.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter


@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FilmScreen(navController: NavController, windowclass: WindowSizeClass) {
    val mainViewModel: MainViewModel = viewModel()
    when (windowclass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {

            Scaffold(
                topBar = {
                    TopNavBar(navController)
                },
                bottomBar = {
                    BottomNavBar(navController)
                }

            )
            {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFEE9898),
                ) {
                    val modifier = Modifier.padding(top = 60.dp, bottom = 60.dp)
                    Films(navController, mainViewModel, modifier = modifier)
                }
            }

        }

        else -> {
            Scaffold(
                topBar = { TopFloatNavBar(navController) },
                bottomBar = {
                    LeftNavBar(navController)
                }
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFEE9898),
                ) {
                    val modifier = Modifier.padding(start = 68.dp)
                    Films(navController, mainViewModel, nbColumns = 4, modifier = modifier)
                }
            }
        }
    }
}

@Composable
fun Films(
    navController: NavController, viewModel: MainViewModel, nbColumns: Int = 2, modifier: Modifier
) {

    val movies by viewModel.movies.collectAsState()

    if (movies.isEmpty()) {
        viewModel.getTrendingMovies()
    }

    if (movies.isNotEmpty()) {
        LazyVerticalGrid(columns = GridCells.Fixed(nbColumns), modifier = modifier) {
            items(movies) { movie ->
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                    modifier = Modifier
                        .fillMaxWidth() // Utilisez toute la largeur de la colonne
                        .padding(10.dp) // Ajoutez un espace autour de chaque carte
                        .height(385.dp) // Définissez la hauteur de la carte
                        .clickable { navController.navigate("MovieDetails/${movie.id}") }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            // verticalArrangement = Arrangement.Center,
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
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(top = 10.dp)
                                    .fillMaxWidth()
                            )
                            Text(
                                text = formatDate(
                                    movie.release_date,
                                    "yyyy-MM-dd",
                                    "dd MMM yyyy",
                                    Locale.FRANCE
                                ),
                                color = Color.Black,
                                modifier = Modifier.padding(top = 10.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

fun formatDate(
    inputDate: String,
    inputDateFormat: String,
    outputDateFormat: String,
    locale: Locale
): String {
    val inputFormat = SimpleDateFormat(inputDateFormat, locale)
    val outputFormat = SimpleDateFormat(outputDateFormat, locale)
    Log.d("date", inputDate)
    Log.d("date", inputFormat.toString())
    val date = inputFormat.parse(inputDate)
    return outputFormat.format(date)
}



