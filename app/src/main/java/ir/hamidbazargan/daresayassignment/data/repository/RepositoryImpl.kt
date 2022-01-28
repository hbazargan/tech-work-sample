package ir.hamidbazargan.daresayassignment.data.repository

import android.util.Log
import androidx.room.InvalidationTracker
import ir.hamidbazargan.daresayassignment.data.db.MovieDao
import ir.hamidbazargan.daresayassignment.data.db.MovieDataBase
import ir.hamidbazargan.daresayassignment.data.mapper.toBookmarkMovieDataBaseEntity
import ir.hamidbazargan.daresayassignment.data.mapper.toMovieEntity
import ir.hamidbazargan.daresayassignment.data.mapper.toPopularMovieDataBaseEntity
import ir.hamidbazargan.daresayassignment.data.mapper.toTopRatedDataBaseEntity
import ir.hamidbazargan.daresayassignment.data.webservice.WebServiceApi
import ir.hamidbazargan.daresayassignment.domain.entity.MovieEntity
import ir.hamidbazargan.daresayassignment.domain.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlin.coroutines.suspendCoroutine

class RepositoryImpl(
    private val webServiceApi: WebServiceApi,
    private val movieDao: MovieDao,
    private val movieDataBase: MovieDataBase
) : Repository {

    override suspend fun fetchPopularMovies(page: Int): List<MovieEntity> {
        Log.d("SAMPLE", "Repository:fetchPopularMovies")
        webServiceApi.getPopularMovies(page).let { response ->
            return mutableListOf<MovieEntity>().apply {
                response.results.forEach { movie ->
                    add(
                        movie.toMovieEntity(
                            response.page,
                            response.totalPage
                        )
                    )
                }
            }
        }
    }

    override suspend fun fetchTopRatedMovies(page: Int): List<MovieEntity> {
        Log.d("SAMPLE", "Repository:fetchTopRatedMovies")
        webServiceApi.getTopRatedMovies(page).let { response ->
            return mutableListOf<MovieEntity>().apply {
                response.results.forEach { movie ->
                    add(
                        movie.toMovieEntity(
                            response.page,
                            response.totalPage
                        )
                    )
                }
            }
        }
    }

    override suspend fun getPopularMoviesChange(page: Int): List<MovieEntity> {
        Log.d("SAMPLE", "Repository:getPopularMovies")
        return movieDao.queryPopularMovies(page).map { movie ->
            movie.toMovieEntity()
        }
    }

    override suspend fun getPopularMoviesChange(): Flow<Boolean> = flow {
        emit(
            emitOnChange()
        )
    }

    private suspend fun emitOnChange(): Boolean {
        var observer: InvalidationTracker.Observer? = null
        val suspendCoroutine: Boolean = suspendCoroutine { continuation ->
            observer = object :
                InvalidationTracker.Observer("popularmovies") {
                override fun onInvalidated(tables: MutableSet<String>) {
                    continuation.resumeWith(
                        Result.success(
                            true
                        )
                    )
                }
            }
            CoroutineScope(Dispatchers.IO).launch {
                observer?.let { obs ->
                    movieDataBase.invalidationTracker.addObserver(obs)
                }
            }
        }
        observer?.let { obs ->
            movieDataBase.invalidationTracker.removeObserver(obs)
        }
        return suspendCoroutine
    }

    override suspend fun getTopRatedMovies(page: Int): List<MovieEntity> {
        Log.d("SAMPLE", "Repository:getTopRatedMovies")
        return movieDao.queryTopRatedMovies(page).map { movie ->
            movie.toMovieEntity()
        }
    }

    override suspend fun getBookmarkMovies(page: Int): List<MovieEntity> {
        Log.d("SAMPLE", "Repository:getBookmarkMovies")
        return movieDao.queryBookmarkMovies(page).map { movie ->
            movie.toMovieEntity()
        }
    }

    override suspend fun getMovie(id: Int): MovieEntity {
        Log.d("SAMPLE", "Repository:getMovie")
        return movieDao.queryMovie(id)?.toMovieEntity() ?: run {
            webServiceApi.getMovie(id).toMovieEntity(1, 1)
        }
    }

    override suspend fun saveMovie(movie: MovieEntity) =
        movieDao.insertBookmarkMovie(listOf(movie.toBookmarkMovieDataBaseEntity()))

    override suspend fun insertPopularMovie(movies: List<MovieEntity>) {
        Log.d("SAMPLE", "Repository:insertPopularMovie")
        movieDao.insertPopularMovie(movies.map { movie ->
            movie.toPopularMovieDataBaseEntity()
        })
    }

    override suspend fun insertTopRatedMovie(movies: List<MovieEntity>) {
        Log.d("SAMPLE", "Repository:insertTopRatedMovie")
        movieDao.insertTopRatedMovie(movies.map { movie ->
            movie.toTopRatedDataBaseEntity()
        })
    }

    override suspend fun clearPopular() {
        Log.d("SAMPLE", "Repository:clearPopular")
        movieDao.clearPopularAll()
    }

    override suspend fun clearTopRated() {
        Log.d("SAMPLE", "Repository:clearTopRated")
        movieDao.clearTopRatedAll()
    }
}