package com.example.firstapplication


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
fun MovieDetails(navController: NavController, movieID: String) {
    val mainViewModel: MainViewModel = viewModel()
    val movieDetails by mainViewModel.movieDetails.collectAsState()

    if (movieDetails.title.isEmpty()) {
        mainViewModel.getFilmDetails()
    }
    if (movieDetails.title.isNotEmpty()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFEE9898))
        ) {
            item(span = {
                GridItemSpan(2)
            }) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Image(
                        painter = rememberImagePainter(
                            data = "https://image.tmdb.org/t/p/w1280" + movieDetails.backdrop_path,
                            builder = {
                                crossfade(true)
                                size(700, 700)
                            }),
                        contentDescription = "Image film ${movieDetails.title}",
                        contentScale = ContentScale.Crop, // Remplit la Box sans déborder
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            item(span = {
                GridItemSpan(2)
            }) {
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = rememberImagePainter(
                            data = "https://image.tmdb.org/t/p/w1280" + movieDetails.poster_path,
                            builder = {
                                crossfade(true)
                                size(400, 400)
                            }),
                        contentDescription = "Image film ${movieDetails.title}",
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
                            text = movieDetails.title,
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
                                        movieDetails.release_date,
                                        "yyyy-dd-MM",
                                        "dd MMM yyyy",
                                        Locale.FRANCE
                                    ),
                                    fontSize = 18.sp,
                                    textAlign = TextAlign.Center
                                )

                                Text(
                                    text = getGenres(movieDetails.genres),
                                    fontSize = 18.sp,
                                    fontStyle = FontStyle.Italic,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }

                    }
                }
            }

            item(span = {
                GridItemSpan(2)
            }) {
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
                        text = movieDetails.overview,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(top = 15.dp, end = 15.dp),
                    )
                }
            }
            if (movieDetails.credits.cast.isNotEmpty()) {

                item(span = {
                    GridItemSpan(2)
                }) {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier.padding(start = 10.dp)
                    ) {
                        Text(
                            text = "Têtes d'affiches",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                        )
                    }
                }



                items(movieDetails.credits.cast.take(10)) { cast ->
                    FloatingActionButton(
                        onClick = { navController.navigate("ActorDetails/${cast.id}") },
                        modifier = Modifier
                            .padding(10.dp)
                            .height(250.dp),
                        containerColor = Color.White
                    ) {
                        Column(
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
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                fontSize = 20.sp,
                                modifier = Modifier.padding(top = 5.dp)
                            )
                            Text(
                                text = cast.character,
                                textAlign = TextAlign.Center,
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


fun getGenres(genres: List<Genre>): String {
    return genres.joinToString(", ") { it.name }
}
