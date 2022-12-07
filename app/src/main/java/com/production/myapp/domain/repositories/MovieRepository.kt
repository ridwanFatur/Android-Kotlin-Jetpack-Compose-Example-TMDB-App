package com.production.myapp.domain.repositories

import com.production.myapp.domain.entities.Movie
import com.production.myapp.domain.entities.MovieDetail
import com.production.myapp.domain.usecases.movie.GetMovieDetailParams
import com.production.myapp.domain.usecases.movie.GetMovieListParams
import com.production.myapp.domain.usecases.movie.GetMovieListWithTotalPagesParams

interface MovieRepository {
    suspend fun getMovieList(params: GetMovieListParams): List<Movie>
    suspend fun getMovieListWithTotalPages(params: GetMovieListWithTotalPagesParams): Pair<List<Movie>, Int>
    suspend fun getMovieDetail(params: GetMovieDetailParams): MovieDetail
}