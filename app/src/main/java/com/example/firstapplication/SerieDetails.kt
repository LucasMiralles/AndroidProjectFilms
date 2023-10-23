package com.example.firstapplication


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import java.util.Locale


@Composable
fun SerieDetails(navController: NavController, serieID: String) {
    val mainViewModel: MainViewModel = viewModel()
    val serieDetails by mainViewModel.serieDetails.collectAsState()

    if (serieDetails.name.isEmpty()) {
        mainViewModel.getSerieDetails()
    }
    if (serieDetails.name.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFEE9898))
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Image(
                        painter = rememberImagePainter(
                            data = "https://image.tmdb.org/t/p/w1280" + serieDetails.backdrop_path,
                            builder = {
                                crossfade(true)
                                size(700, 700)
                            }),
                        contentDescription = "Image série ${serieDetails.name}",
                        contentScale = ContentScale.Crop, // Remplit la Box sans déborder
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            item {
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = rememberImagePainter(
                            data = "https://image.tmdb.org/t/p/w1280" + serieDetails.poster_path,
                            builder = {
                                crossfade(true)
                                size(400, 400)
                            }),
                        contentDescription = "Image série ${serieDetails.name}",
                        modifier = Modifier
                            .offset(y = (-30).dp)
                            .padding(start = 25.dp, end = 10.dp, top = 5.dp)
                            .clip(shape = RoundedCornerShape(16.dp))
                    )
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(start = 20.dp, end = 15.dp)
                    ) {
                        Text(
                            text = serieDetails.name,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,
                            modifier = Modifier.padding(vertical = 10.dp),
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    (Color.Black.copy(alpha = 0.2f)),
                                    RoundedCornerShape(16.dp)
                                )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(5.dp) // Espacement vertical entre les éléments
                            ) {
                                Text(
                                    text = formatDate(
                                        serieDetails.first_air_date,
                                        "yyyy-dd-MM",
                                        "dd MMM yyyy",
                                        Locale.FRANCE
                                    ),
                                    fontSize = 18.sp,
                                    textAlign = TextAlign.Center
                                )

                                Text(
                                    text = getGenres(serieDetails.genres),
                                    fontSize = 18.sp,
                                    fontStyle = FontStyle.Italic,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }

                    }
                }
            }

            item {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Text(
                        text = "Synopsis",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                    )
                    Text(
                        text = serieDetails.overview,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(top = 15.dp, end = 15.dp),
                    )
                }
            }
            if (serieDetails.credits.cast.isNotEmpty()) {
                item {
                    Text(
                        text = "Têtes d'affiches",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                    )
                }

                /*      LazyVerticalGrid(
                        columns = GridCells.Fixed(2), // 2 colonnes
                    ) {*/

                items(serieDetails.credits.cast.take(10)) { cast ->
                    FloatingActionButton(
                        onClick = { navController.navigate("ActorDetails/${cast.id}") },
                        modifier = Modifier.padding(20.dp),
                        containerColor = Color.White,
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Image(
                                painter = rememberImagePainter(
                                    data = "https://image.tmdb.org/t/p/w780" + cast.profile_path,
                                    builder = {
                                        crossfade(true)
                                        size(350, 400)
                                    }
                                ),
                                contentDescription = "Image film ${cast.name}",
                                modifier = Modifier
                                    .padding(5.dp)
                                    .size(150.dp) // Taille de l'image
                            )
                            Text(
                                text = cast.name,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                fontSize = 20.sp,
                                modifier = Modifier.padding(top = 5.dp)
                            )
                            Text(
                                text = cast.character,
                                color = Color.Black,
                                fontSize = 18.sp,
                                modifier = Modifier.padding(top = 15.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

