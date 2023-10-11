package com.example.firstapplication

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.asImageBitmap




@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScreenContent() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = topAppBarColors(
                    containerColor = Color(0xFFCC1228),
                   // titleContentColor = Color(0xFFFFFFFF),
                ),
                title = {
                    Text(
                        "Top app bar",
                        modifier = Modifier.padding(start = 16.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 25.sp,
                        color = Color.White,
                    )

                },
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Arrow back",
                            tint = Color.White,
                        )
                    }
                },
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
        },
        bottomBar = {
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
                            Image(
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
                            Image(
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
                            Image(
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

        ,
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text =
                """
                    Test Scaffold c'est RELOU !!!
                """.trimIndent(),
            )
        }
    }
}





