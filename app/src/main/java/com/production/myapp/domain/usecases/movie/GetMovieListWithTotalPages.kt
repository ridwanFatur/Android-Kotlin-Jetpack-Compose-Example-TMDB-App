package com.production.myapp.domain.usecases.movie

import android.content.Context
import android.util.Log
import com.production.myapp.R
import com.production.myapp.core.ResultNetwork
import com.production.myapp.domain.entities.Movie
import com.production.myapp.domain.repositories.MovieRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieListWithTotalPages @Inject constructor(
    private val repository: MovieRepository,
) {
    operator fun invoke(params: GetMovieListWithTotalPagesParams, context: Context): Flow<ResultNetwork<Pair<List<Movie>, Int>>> =
        flow {
            try {
                emit(ResultNetwork.Loading<Pair<List<Movie>, Int>>())
                delay(800)

                val result = repository.getMovieListWithTotalPages(params)
                emit(ResultNetwork.Success<Pair<List<Movie>, Int>>(result))
            } catch (httpException: HttpException) {
                try {
                    val strError: String =
                        httpException.response()?.errorBody()?.charStream()?.readText().toString()
                    val errorObj = JSONObject(strError)
                    val statusMessage = errorObj.getString("status_message")
                    emit(ResultNetwork.Error<Pair<List<Movie>, Int>>(statusMessage))
                } catch (e: Exception) {
                    emit(
                        ResultNetwork.Error<Pair<List<Movie>, Int>>(
                            httpException.localizedMessage ?: context.getString(R.string.UnexpectedErrorMessage)
                        )
                    )
                }
            } catch (e: IOException) {
                emit(ResultNetwork.Error<Pair<List<Movie>, Int>>(context.getString(R.string.CheckInternetConnectionMessage)))
            } catch (e: Exception) {
                Log.d("TAG", e.toString())
                emit(ResultNetwork.Error<Pair<List<Movie>, Int>>(context.getString(R.string.SomethingWentWrongMessage)))
            }
        }
}

data class GetMovieListWithTotalPagesParams(
    val page: Int,
)