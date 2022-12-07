package com.production.myapp.domain.usecases.movie

import com.production.myapp.core.ResultNetwork
import com.production.myapp.domain.entities.MovieDetail
import com.production.myapp.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieDetail @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(params: GetMovieDetailParams): Flow<ResultNetwork<MovieDetail>> = flow {
        try {
            emit(ResultNetwork.Loading<MovieDetail>())
            val result = repository.getMovieDetail(params)
            emit(ResultNetwork.Success<MovieDetail>(result))
        } catch (httpException: HttpException) {
            try {
                val strError: String =
                    httpException.response()?.errorBody()?.charStream()?.readText().toString()
                val errorObj = JSONObject(strError)
                val statusMessage = errorObj.getString("status_message")
                emit(ResultNetwork.Error<MovieDetail>(statusMessage))
            } catch (e: Exception) {
                emit(
                    ResultNetwork.Error<MovieDetail>(
                        httpException.localizedMessage ?: "An unexpected error occurred"
                    )
                )
            }
        } catch (e: IOException) {
            emit(ResultNetwork.Error<MovieDetail>("Couldn't reach server. Check your internet connection."))
        } catch (e: Exception) {
            emit(ResultNetwork.Error<MovieDetail>("Something went wrong"))
        }
    }
}

data class GetMovieDetailParams(
    val id: Int,
)