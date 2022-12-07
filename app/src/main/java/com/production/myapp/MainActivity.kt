package com.production.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavGraph
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.production.myapp.core.route.ScreenRoute
import com.production.myapp.presentation.NavGraphs
import com.production.myapp.ui.theme.MyAppTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            val navController = rememberNavController()
            MyAppTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
//                NavHost(
//                    navController = navController,
//                    startDestination = ScreenRoute.MainScreen.route,
//                ) {
//                    composable(route = ScreenRoute.MainScreen.route) {
//                        MainScreen(navController,)
//                    }
//                    composable(
//                        route = ScreenRoute.MovieDetailScreen.route,
//                    ) {
//                        MovieDetailScreen(navController)
//                    }
//                }
            }
        }
    }
}