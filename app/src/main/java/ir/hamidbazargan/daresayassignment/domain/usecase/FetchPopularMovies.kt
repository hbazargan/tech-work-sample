package ir.hamidbazargan.daresayassignment.domain.usecase

import android.util.Log
import ir.hamidbazargan.daresayassignment.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchPopularMovies(
    private val repository: Repository
) : UseCaseWithParamReturn<Int, Int?> {
    override suspend fun execute(page: Int): Int? {
        Log.d("SAMPLE", "FetchPopularMovies")
        repository.fetchPopularMovies(page).also { movies ->
            withContext(Dispatchers.IO) {
                if (page == 1) {
                    Log.d("SAMPLE", "FetchPopularMovies:page:1")
                    repository.clearPopular()
                }
                repository.insertPopularMovie(movies)
            }
            return if (
                movies.isNotEmpty().and(
                    (movies.last().page < movies.last().totalPage)
                )
            ) {
                Log.d("SAMPLE", "FetchPopularMovies:page:${movies.last().page + 1}")
                movies.last().page + 1
            } else {
                Log.d("SAMPLE", "FetchPopularMovies:page:null")
                null
            }
        }
    }
}