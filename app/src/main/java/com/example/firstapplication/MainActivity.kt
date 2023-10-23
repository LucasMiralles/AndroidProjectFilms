package com.example.firstapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firstapplication.ui.theme.FirstApplicationTheme


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstApplicationTheme {
                val windowClass = calculateWindowSizeClass(activity = this)

                // Initialize NavHostController
                val navController = rememberNavController()

                // Create a NavHost for navigation
                NavHost(
                    navController = navController,
                    startDestination = "profile"
                ) {
                    composable("profile") {
                        // Composable for Profile screen
                        Profil(navController, windowClass)
                    }
                    composable("films") {
                        // Composable for Films screen
                        FilmScreen(navController, windowClass)
                    }
                    composable("series") {
                        // Composable for Series screen
                        SerieScreen(navController, windowClass)
                    }
                    composable("actors") {
                        // Composable for Actors screen
                        ActorScreen(navController, windowClass)
                    }
                    composable("MovieDetails/{movieID}") { backStackEntry ->
                        val movieId = backStackEntry.arguments?.getString("movieId") ?: ""
                        // Composable for Films Details screen
                        MovieDetails(navController, movieId)
                    }
                    composable("SerieDetails/{serieID}") { backStackEntry ->
                        val serieId = backStackEntry.arguments?.getString("serieId") ?: ""
                        // Composable for Series Details screen
                        SerieDetails(navController, serieId)
                    }
                    composable("ActorDetails/{actorID}") { backStackEntry ->
                        val actorId = backStackEntry.arguments?.getString("actorId") ?: ""
                        // Composable for Actors Details screen
                        ActorDetails(navController, actorId)
                    }
                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirstApplicationTheme {
    }
}