package com.production.myapp.presentation.main_screen.widgets

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.production.myapp.domain.entities.Movie
import com.production.myapp.ui.theme.MyAppTheme
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.production.myapp.core.utils.DateHelper
import com.production.myapp.R

@Composable
fun MovieView(movie: Movie, onPressed: (movie: Movie) -> Unit) {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .padding(PaddingValues(horizontal = 16.dp, vertical = 10.dp))
            .fillMaxWidth()
            .height(150.dp),
        elevation = 10.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clickable {
                    onPressed(movie)
                },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.4f)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://image.tmdb.org/t/p/w185/${movie.posterPath}")
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = R.drawable.ic_movie),
                    contentDescription = "Image Poster Path",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(1f)
                    .padding(20.dp)
            ) {
                Text(
                    text = movie.name,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = DateHelper.monthNameDateFormat(movie.firstAirDate),
                    style = TextStyle(
                        fontSize = 16.sp,
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyAppTheme {
        MovieView(
            movie = Movie(
                backdropPath = "/6GIf7uiQ4Y5xPzIRkTct2NIyBqK.jpg",
                id = 365,
                originalLanguage = "en",
                originalName = "Jericho",
                overview = "",
                popularity = 18.237,
                posterPath = "/a57H9UsS388Av2LSLKO9inNmY7j.jpg",
                firstAirDate = "2006-09-20",
                name = "Jericho\nTest\nTest",
                voteAverage = 7.441,
                voteCount = 363
            ),
            onPressed = { movie ->
                Log.d("TAG", "Click ${movie.name}")
            }
        )
    }
}