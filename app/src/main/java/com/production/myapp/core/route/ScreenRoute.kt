package com.production.myapp.core.route

sealed class ScreenRoute(val route: String) {
    object MainScreen: ScreenRoute("main_screen")
    object MovieDetailScreen: ScreenRoute("movie_detail_screen")
}