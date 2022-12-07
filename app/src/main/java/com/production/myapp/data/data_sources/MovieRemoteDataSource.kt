package com.production.myapp.data.data_sources

import com.production.myapp.core.constants.Urls
import com.production.myapp.data.models.MovieDetailModel
import com.production.myapp.data.models.response.movie.GetMovieListResponse
import com.production.myapp.data.models.response.movie.GetMovieListWithTotalPagesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieRemoteDataSource {
    @GET(Urls.popularTvShow)
    suspend fun getMovieList(@Query("page") page: String): GetMovieListResponse

    @GET(Urls.popularTvShow)
    suspend fun getMovieListWithTotalPages(@Query("page") page: String): GetMovieListWithTotalPagesResponse

    @GET(Urls.detailTvShow + "/{id}")
    suspend fun getMovieDetail(@Path("id") id: String): MovieDetailModel
}