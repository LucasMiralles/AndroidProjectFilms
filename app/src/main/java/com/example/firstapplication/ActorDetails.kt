package com.example.firstapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import java.util.regex.Pattern

@Composable
fun ActorDetails(navController: NavController, actorID: String) {
    val mainViewModel: MainViewModel = viewModel()
    val actorDetails by mainViewModel.actorDetails.collectAsState()

    if (actorDetails.name.isEmpty()) {
        mainViewModel.getActorDetails()
    }

    if (actorDetails.name.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFEE9898))
        ) {
            item {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                 //   modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    // Nom de la personne
                    Text(
                        text = actorDetails.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        modifier = Modifier
                            .padding(top = 10.dp, bottom = 10.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    Image(
                        painter = rememberImagePainter(
                            data = "https://image.tmdb.org/t/p/h632" + actorDetails.profile_path,
                            builder = {
                                crossfade(true)
                                size(700, 700)
                            }),
                        contentDescription = "Image acteur ${actorDetails.name}",
                        modifier = Modifier
                            .padding(start = 15.dp, end = 15.dp)
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.size(10.dp))

                    Box(
                        modifier = Modifier
                            //  .fillMaxWidth()
                            .background(
                                (Color.Black.copy(alpha = 0.2f)), // Ajustez la valeur d'alpha ici
                                RoundedCornerShape(16.dp)
                            )
                    ) {
                        Column(
                            modifier = Modifier
                                //.fillMaxWidth()
                                .padding(5.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(5.dp) // Espacement vertical entre les éléments
                        ) {
                            if (actorDetails.gender == 1) {
                                Text(
                                    text = "Sexe : Femme",
                                    modifier = Modifier.padding(top = 5.dp, end = 5.dp)
                                )
                            } else {
                                Text(
                                    text = "Sexe : Homme",
                                    modifier = Modifier.padding(top = 5.dp, end = 5.dp)
                                )
                            }

                            if (actorDetails.known_for_department != "") {
                                Text(
                                    text = "Métier : " + actorDetails.known_for_department,
                                    modifier = Modifier.padding(top = 5.dp, end = 5.dp)
                                )
                            }

                            var checkBirthday = false
                            if (actorDetails.birthday != null) {
                                checkBirthday =
                                    Pattern.matches("^\\d{4}-\\d{2}-\\d{2}\$", actorDetails.birthday)
                            }

                            if (actorDetails.place_of_birth != "" && checkBirthday) {
                                Text(
                                    text = "Lieu de naissance : " + actorDetails.place_of_birth,
                                    fontStyle = FontStyle.Italic,
                                    modifier = Modifier.padding(top = 5.dp, end = 5.dp)
                                )

                                Text(
                                    text = "Anniversaire : " + formatDate(
                                        actorDetails.birthday,
                                        "yyyy-dd-MM",
                                        "dd MMM yyyy",
                                        Locale.FRANCE
                                    ),
                                    modifier = Modifier.padding(top = 5.dp),
                                    fontSize = 15.sp
                                )
                            }
                        }
                    }


                    // Synopsis
                    if (actorDetails.biography != "") {
                        Text(
                            text = "Biographie",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,
                            modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                        )

                        Text(
                            text = actorDetails.biography,
                            textAlign = TextAlign.Justify,
                            modifier = Modifier.padding(top = 15.dp, end = 15.dp)
                        )
                    }
                }
            }
        }
    }
}
