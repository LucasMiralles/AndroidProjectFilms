package com.example.firstapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun Profil(navController: NavController,
           windowClass: WindowSizeClass) {
    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Image(
                    painter = painterResource(id = R.drawable.photo_identite),
                    contentDescription = "Moi",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(300.dp)
                        .clip(CircleShape)
                        .padding(10.dp)

                )

                Text(
                    text = "Lucas Miralles",
                    fontWeight = FontWeight.Bold, // Mettez en gras
                    fontSize = 40.sp, // Taille de police personnalisée
                    color = Color.Black, // Couleur du texte
                    modifier = Modifier.padding(10.dp)
                )
                Text(
                    text = "Élève Ingénieur ISIS - FIE4",
                    fontSize = 25.sp, // Taille de police personnalisée
                    color = Color.Black, // Couleur du texte
                    modifier = Modifier.padding(10.dp)
                )
                Spacer(modifier = Modifier.height(30.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.gmail),
                            contentDescription = "Mail",
                            modifier = Modifier
                                .size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "lucas.miralles09@gmail.com",
                            fontSize = 20.sp,
                            color = Color.Black,
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.linkedin),
                            contentDescription = "linkedin",
                            modifier = Modifier
                                .size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "www.linkedin.com/in/lucas-miralles",
                            fontSize = 20.sp,
                            color = Color.Black,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Démarrer")
                }
            }

        else -> {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.photo_identite),
                        contentDescription = "Moi",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                            .padding(10.dp)

                    )

                    Text(
                        text = "Lucas Miralles",
                        fontWeight = FontWeight.Bold, // Mettez en gras
                        fontSize = 40.sp, // Taille de police personnalisée
                        color = Color.Black, // Couleur du texte
                        modifier = Modifier.padding(10.dp)
                    )
                    Text(
                        text = "Élève Ingénieur ISIS - FIE4",
                        fontSize = 25.sp, // Taille de police personnalisée
                        color = Color.Black, // Couleur du texte
                        modifier = Modifier.padding(10.dp)
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.gmail),
                            contentDescription = "Mail",
                            modifier = Modifier
                                .size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "lucas.miralles09@gmail.com",
                            fontSize = 20.sp,
                            color = Color.Black,
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.linkedin),
                            contentDescription = "linkedin",
                            modifier = Modifier
                                .size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "www.linkedin.com/in/lucas-miralles",
                            fontSize = 20.sp,
                            color = Color.Black,
                        )


                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Démarrer")
                    }
                }

            }
        }
    }
}
