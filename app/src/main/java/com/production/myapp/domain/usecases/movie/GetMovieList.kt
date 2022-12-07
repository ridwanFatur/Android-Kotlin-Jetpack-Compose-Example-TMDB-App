package com.production.myapp.domain.usecases.movie

import android.util.Log
import com.production.myapp.core.ResultNetwork
import com.production.myapp.domain.entities.Movie
import com.production.myapp.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieList @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(params: GetMovieListParams): Flow<ResultNetwork<List<Movie>>> = flow {
        try {
            emit(ResultNetwork.Loading<List<Movie>>())
            val result = repository.getMovieList(params)
            emit(ResultNetwork.Success<List<Movie>>(result))
        } catch (httpException: HttpException) {
            try {
                val strError: String =
                    httpException.response()?.errorBody()?.charStream()?.readText().toString()
                val errorObj = JSONObject(strError)
                val statusMessage = errorObj.getString("status_message")
                emit(ResultNetwork.Error<List<Movie>>(statusMessage))
            } catch (e: Exception) {
                emit(
                    ResultNetwork.Error<List<Movie>>(
                        httpException.localizedMessage ?: "An unexpected error occurred"
                    )
                )
            }
        } catch (e: IOException) {
            emit(ResultNetwork.Error<List<Movie>>("Couldn't reach server. Check your internet connection."))
        } catch (e: Exception) {
            Log.d("TAG", e.toString())
            emit(ResultNetwork.Error<List<Movie>>("Something went wrong"))
        }
    }
}

data class GetMovieListParams(
    val page: Int,
)