package com.production.myapp.data.repositories

import com.production.myapp.data.data_sources.MovieRemoteDataSource
import com.production.myapp.data.models.toEntity
import com.production.myapp.domain.entities.Movie
import com.production.myapp.domain.entities.MovieDetail
import com.production.myapp.domain.repositories.MovieRepository
import com.production.myapp.domain.usecases.movie.GetMovieDetailParams
import com.production.myapp.domain.usecases.movie.GetMovieListParams
import com.production.myapp.domain.usecases.movie.GetMovieListWithTotalPagesParams
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource
) : MovieRepository {
    override suspend fun getMovieList(params: GetMovieListParams): List<Movie> {
        return remoteDataSource.getMovieList(page = params.page.toString()).results.map { it.toEntity() }
    }

    override suspend fun getMovieDetail(params: GetMovieDetailParams): MovieDetail {
        return  remoteDataSource.getMovieDetail(id = params.id.toString()).toEntity()
    }

    override suspend fun getMovieListWithTotalPages(params: GetMovieListWithTotalPagesParams): Pair<List<Movie>, Int> {
        val result = remoteDataSource.getMovieListWithTotalPages(page = params.page.toString())
        return Pair(result.results.map { it.toEntity() }, result.totalPages)
    }
}