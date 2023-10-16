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

        val viewModel : MainViewModel by viewModels()
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
                            FilmScreen(navController)
                        }
                        composable("series") {
                            // Composable for Films screen
                            SerieScreen(navController)
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