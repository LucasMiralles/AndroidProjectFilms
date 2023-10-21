package com.example.firstapplication

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Surface
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter


@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ActorScreen(navController: NavController) {
    val mainViewModel: MainViewModel = viewModel()

    Scaffold(
        topBar = {
            TopNavBar(navController)
        },
        bottomBar = {
            BottomNavBar(navController)
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFEE9898),
        ) {
            val modifier = Modifier.padding(top = 60.dp, bottom = 60.dp)
            Actors(navController, mainViewModel, modifier = modifier)
        }
    }
}


@Composable
fun Actors(
    navController: NavController, viewModel: MainViewModel, modifier: Modifier
) {

    val actors by viewModel.actors.collectAsState()

    if (actors.isEmpty()) {
        viewModel.getTrendingActors()
    }

    if (actors.isNotEmpty()) {
        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = modifier) {
            items(actors) { actor ->
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                    modifier = Modifier
                        .fillMaxWidth() // Utilisez toute la largeur de la colonne
                        .padding(10.dp) // Ajoutez un espace autour de chaque carte
                        .height(325.dp) // Définissez la hauteur de la carte
                        .clickable { navController.navigate("ActorDetails/${actor.id}")}
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp) // Ajoutez un espace à l'intérieur de la carte
                    ) {
                        // Photo de l'acteur
                        Image(
                            painter = rememberImagePainter(
                                data = "https://image.tmdb.org/t/p/w780" + actor.profile_path,
                                builder = {
                                    crossfade(true)
                                    size(350, 400)
                                }
                            ),
                            contentDescription = "Image serie ${actor.name}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp) // Ajustez la hauteur de l'image
                        )

                        // Nom de l'acteur
                        Text(
                            text = actor.name,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}