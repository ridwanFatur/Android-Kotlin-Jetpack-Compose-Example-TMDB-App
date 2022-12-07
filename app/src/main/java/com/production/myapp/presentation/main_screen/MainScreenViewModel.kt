package com.production.myapp.presentation.main_screen

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.production.myapp.core.ResultNetwork
import com.production.myapp.domain.entities.Movie
import com.production.myapp.domain.usecases.movie.GetMovieListWithTotalPages
import com.production.myapp.domain.usecases.movie.GetMovieListWithTotalPagesParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val useCaseGetMovieListWithTotalPages: GetMovieListWithTotalPages,
    private val application: Application,
) : ViewModel() {
    var movieListResult: ResultNetwork<List<Movie>>? by  mutableStateOf(null)
        private set

    init {
        getMovieList()
    }

    fun getMovieList() {
        useCaseGetMovieListWithTotalPages(GetMovieListWithTotalPagesParams(page = 1), application.applicationContext).onEach { result ->
            when (result){
                is ResultNetwork.Error -> {
                    Log.d("TAG","Error")
                    movieListResult =
                        ResultNetwork.Error(message = result.message ?: "An unexpected error occurred")
                }
                is ResultNetwork.Loading -> {
                    Log.d("TAG","Loading")
                    movieListResult = ResultNetwork.Loading()
                }
                is ResultNetwork.Success -> {
                    movieListResult = ResultNetwork.Success(result.data?.first ?: emptyList())
                    Log.d("TAG","Success")
                    Log.d("TAG", result.data?.second.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun retryGetMovieList(){
        if (movieListResult is ResultNetwork.Error){
            Log.d("TAG", "Retry")
            getMovieList()
        }
    }
}