package ir.hamidbazargan.daresayassignment.domain.usecase

import android.util.Log
import ir.hamidbazargan.daresayassignment.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchTopRatedMovies(
    private val repository: Repository
) : UseCaseWithParamReturn<Int, Int?> {
    override suspend fun execute(page: Int): Int? {
        Log.d("SAMPLE", "FetchTopRatedMovies")
        repository.fetchTopRatedMovies(page).also { movies ->
            withContext(Dispatchers.IO) {
                if (page == 1) {
                    repository.clearTopRated()
                }
                repository.insertTopRatedMovie(movies)
            }
            return if (
                movies.isNotEmpty().and(
                    (movies.last().page < movies.last().totalPage)
                )
            ) {
                return movies.last().page + 1
            } else {
                null
            }
        }
    }
}