package com.example.firstapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar(navController: NavController) {
    var searchActive by remember { mutableStateOf(false) } // État pour indiquer si la recherche est active
    var searchText by remember { mutableStateOf("") } // État pour stocker le texte de recherche
    val imeAction = rememberUpdatedState(ImeAction.Done)
    val keyboardController = LocalSoftwareKeyboardController.current
    
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFCC1228),
            titleContentColor = Color(0xFFFFFFFF),
        ),
        title = {
            Text(
                text= if (searchActive) "Recherchez des films et des séries" else "Top app bar",
                modifier = Modifier.padding(start = 16.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 25.sp,
                color = Color.White,
            )

        },
        navigationIcon = {
            IconButton(onClick = { navController.navigate("profile") }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Arrow back",
                    tint = Color.White,
                )
            }
        },
        modifier = Modifier.fillMaxWidth(),
        actions = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = Color.White,
                )
            }
        },
    )
}

@Composable
fun BottomNavBar(navController: NavController) {
    BottomAppBar(
        backgroundColor = Color(0xFFCC1228),
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.padding(start = 35.dp)) // Espace à gauche

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.battant_blanc),
                        contentDescription = "Movies",
                        modifier = Modifier
                            .size(30.dp)
                    )
                    Text(
                        "Films",
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.weight(1f)) // Espace égal au milieu

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.television_blanche),
                        contentDescription = "Series",
                        modifier = Modifier
                            .size(30.dp)
                    )
                    Text(
                        "Séries",
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.weight(1f)) // Espace égal entre les images

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_person_24),
                        contentDescription = "Movies",
                        modifier = Modifier
                            .size(30.dp)
                    )
                    Text(
                        "Acteurs",
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.padding(end = 35.dp)) // Espace à droite
            }
        }
    )
}







