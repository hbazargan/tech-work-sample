package ir.hamidbazargan.daresayassignment.domain.repository

import ir.hamidbazargan.daresayassignment.domain.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getPopularMoviesChange(page: Int): List<MovieEntity>
    suspend fun getPopularMoviesChange(): Flow<Boolean>
    suspend fun getTopRatedMovies(page: Int): List<MovieEntity>
    suspend fun getBookmarkMovies(page: Int): List<MovieEntity>
    suspend fun getMovie(id: Int): MovieEntity
    suspend fun saveMovie(movie: MovieEntity)
    suspend fun fetchPopularMovies(page: Int): List<MovieEntity>
    suspend fun fetchTopRatedMovies(page: Int): List<MovieEntity>
    suspend fun insertPopularMovie(movies: List<MovieEntity>)
    suspend fun insertTopRatedMovie(movies: List<MovieEntity>)
    suspend fun clearPopular()
    suspend fun clearTopRated()
}