package com.production.myapp.presentation.main_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.production.myapp.core.ResultNetwork
import com.production.myapp.core.route.ScreenRoute
import com.production.myapp.domain.entities.Movie
import com.production.myapp.presentation.destinations.MovieDetailScreenDestination
import com.production.myapp.presentation.destinations.ProfileScreenDestination
import com.production.myapp.presentation.main_screen.widgets.MovieView
import com.production.myapp.presentation.movie_detail_screen.MovieDetailScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun MainScreen(
    navigator: DestinationsNavigator,
    mainScreenViewModel: MainScreenViewModel = hiltViewModel()
) {
    Scaffold(backgroundColor = Color.White) {
        ContentView(
            resultNetwork = mainScreenViewModel.movieListResult,
            onPressedRetry = {
                mainScreenViewModel.retryGetMovieList()
            },
            onPressedMovieItem = {
                navigator.navigate(MovieDetailScreenDestination(it))
            }
        )
    }
}

@Composable
fun ContentView(
    resultNetwork: ResultNetwork<List<Movie>>?,
    onPressedRetry: () -> Unit,
    onPressedMovieItem: (movie: Movie) -> Unit
) {
    if (resultNetwork == null) {
        Spacer(modifier = Modifier)
    } else {
        when (resultNetwork) {
            is ResultNetwork.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "${resultNetwork.message}"
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(onClick = {
                            onPressedRetry()
                        }) {
                            Text(text = "Retry")
                        }
                    }
                }
            }
            is ResultNetwork.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
            is ResultNetwork.Success -> {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(0.dp),
                        contentPadding = PaddingValues(horizontal = 0.dp, vertical = 10.dp)
                    ) {
                        items(
                            count = resultNetwork.data?.size!!,
                            itemContent = { it ->
                                val movie: Movie = resultNetwork.data[it]
                                MovieView(
                                    movie = movie,
                                    onPressed = {
                                        onPressedMovieItem(it)
                                    }
                                )
                            }
                        )

                    }
                }
            }
        }
    }

}

@Destination
@Composable
fun ProfileScreen(
    navigator: DestinationsNavigator,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Profile Screen:", textAlign = TextAlign.Center)
        Button(onClick = {

        }) {
            Text("Go to Post Screen")
        }
    }
}
