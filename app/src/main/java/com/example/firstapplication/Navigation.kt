package com.example.firstapplication

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar(navController: NavController) {
    val mainViewModel: MainViewModel = viewModel()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    var searchActive by remember { mutableStateOf(false) } // État pour indiquer si la recherche est active
    var searchText by remember { mutableStateOf("") } // État pour stocker le texte de recherche
    var searchBarVisible by remember { mutableStateOf(false) } // État pour indiquer si la barre de recherche est visible

    if (!searchBarVisible) {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFFCC1228),
                titleContentColor = Color(0xFFFFFFFF),
            ),
            title = {
                Text(
                    text = "TV Time",
                    modifier = Modifier.padding(start = 16.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 25.sp,
                    color = Color.White,
                )

            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Arrow back",
                        tint = Color.White,
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            actions = {
                IconButton(onClick = { searchBarVisible = true }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search",
                        tint = Color.White,
                    )
                }
            },
        )
    } else {
        TextField(
            value = searchText,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFCC1228),
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White,
                cursorColor = Color.White,
            ),
            onValueChange = { searchText = it },
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
                .clip(RoundedCornerShape(40.dp)),
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.White),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = androidx.compose.ui.text.input.ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    // Effectuez l'action de recherche ici
                    if (currentDestination?.route == "films") {
                        mainViewModel.searchMovie(searchText)
                    }
                    if (currentDestination?.route == "series") {
                        mainViewModel.searchSerie(searchText)
                    }
                    if (currentDestination?.route == "actors") {
                        mainViewModel.searchActor(searchText)
                    }
                    searchActive = false
                    searchBarVisible = false
                }
            ),
            leadingIcon = {
                IconButton(onClick = { searchBarVisible = false }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = { searchText = "" }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear",
                        tint = Color.White,
                    )
                }
            },
        )
    }
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

                IconButton(onClick = { navController.navigate("films") }) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.battant_blanc),
                            tint = Color.White,
                            contentDescription = "Movies",
                            modifier = Modifier
                                .size(30.dp)
                        )
                        Text(
                            "Films",
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f)) // Espace égal au milieu

                IconButton(onClick = { navController.navigate("series") }) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.television_blanche),
                            tint = Color.White,
                            contentDescription = "Series",
                            modifier = Modifier
                                .size(30.dp)
                        )
                        Text(
                            "Séries",
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f)) // Espace égal entre les images

                IconButton(onClick = { navController.navigate("actors") }) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_person_24),
                            tint = Color.White,
                            contentDescription = "Movies",
                            modifier = Modifier
                                .size(30.dp)
                        )
                        Text(
                            "Acteurs",
                            color = Color.White
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(end = 35.dp)) // Espace à droite
            }

        }
    )
}

@Composable
fun LeftNavBar(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxHeight()
            .background(Color(0xFFCC1228))
            .padding(start = 10.dp, end = 10.dp)
    ) {

        IconButton(onClick = { navController.navigate("films") }) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.battant_blanc),
                    tint = Color.White,
                    contentDescription = "Movies",
                    modifier = Modifier
                        .size(30.dp)
                )
                Text(
                    "Films",
                    color = Color.White
                )
            }
        }


        IconButton(onClick = { navController.navigate("series") }) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.television_blanche),
                    tint = Color.White,
                    contentDescription = "Series",
                    modifier = Modifier
                        .size(30.dp)
                )
                Text(
                    "Séries",
                    color = Color.White
                )
            }
        }


        IconButton(onClick = { navController.navigate("actors") }) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_person_24),
                    tint = Color.White,
                    contentDescription = "Movies",
                    modifier = Modifier
                        .size(30.dp)
                )
                Text(
                    "Acteurs",
                    color = Color.White
                )
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopFloatNavBar(navController: NavController) {
    val mainViewModel: MainViewModel = viewModel()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    var searchActive by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    var searchBarVisible by remember { mutableStateOf(false) }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (!searchBarVisible) {
            FloatingActionButton(
                containerColor = Color(0xFFCC1228),
                onClick = { searchBarVisible = true },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .clip(CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = Color.White,
                )
            }
        } else {
            TextField(
                value = searchText,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFFCC1228),
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White,
                    cursorColor = Color.White,
                ),
                onValueChange = { searchText = it },
                modifier = Modifier
                    .height(60.dp)
                    .padding(start = 80.dp, end = 16.dp, top = 5.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .fillMaxWidth(),
                textStyle = androidx.compose.ui.text.TextStyle(color = Color.White),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = androidx.compose.ui.text.input.ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        // Effectuez l'action de recherche ici
                        if (currentDestination?.route == "films") {
                            mainViewModel.searchMovie(searchText)
                        }
                        if (currentDestination?.route == "series") {
                            mainViewModel.searchSerie(searchText)
                        }
                        if (currentDestination?.route == "actors") {
                            mainViewModel.searchActor(searchText)
                        }
                        searchActive = false
                        searchBarVisible = false
                    }
                ),
                leadingIcon = {
                    IconButton(onClick = { searchBarVisible = false }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White,
                        )
                    }
                },
                trailingIcon = {
                    IconButton(onClick = { searchText = "" }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear",
                            tint = Color.White,
                        )
                    }
                },
            )
        }
    }
}







