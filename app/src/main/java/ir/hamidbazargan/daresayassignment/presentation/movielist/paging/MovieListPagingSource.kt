package ir.hamidbazargan.daresayassignment.presentation.movielist.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import ir.hamidbazargan.daresayassignment.domain.entity.MovieEntity
import ir.hamidbazargan.daresayassignment.domain.usecase.UseCaseWithParamReturn
import ir.hamidbazargan.daresayassignment.domain.usecase.UseCaseWithReturn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

class MovieListPagingSource(
    private val getChangeUsecase: UseCaseWithReturn<Flow<Boolean>>?,
    private val usecase: UseCaseWithParamReturn<Int, List<MovieEntity>>
) : PagingSource<Int, MovieEntity>() {

    private val registeredObserver: AtomicBoolean = AtomicBoolean(false)

    override fun getRefreshKey(state: PagingState<Int, MovieEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieEntity> {
        if (registeredObserver.compareAndSet(false, true)) {
            CoroutineScope(Dispatchers.IO).launch {
                getChangeUsecase?.execute()?.collect {
                    invalidate()
                }
            }
        }
        try {
            Log.d("SAMPLE", "MovieListPagingSource")
            val page = params.key ?: 1
            var nextKey: Int? = null
            var moviesList: MutableList<MovieEntity> = mutableListOf()
            withContext(Dispatchers.IO) {
                usecase.execute(page).also { movies ->
                    moviesList.addAll(movies)
                    nextKey = if (movies.size >= 20) {
                        page + 1
                    } else {
                        null
                    }
                }
            }
            return LoadResult.Page(
                data = moviesList,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}