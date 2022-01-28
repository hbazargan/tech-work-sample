package ir.hamidbazargan.daresayassignment.presentation.movielist.paging

import android.util.Log
import androidx.paging.*
import ir.hamidbazargan.daresayassignment.domain.entity.MovieEntity
import ir.hamidbazargan.daresayassignment.domain.usecase.UseCaseWithParamReturn

@OptIn(ExperimentalPagingApi::class)
class MovieListRemoteMediator(
    private val fetchUseCase: UseCaseWithParamReturn<Int, Int?>
) : RemoteMediator<Int, MovieEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        return try {
            Log.d("SAMPLE","MovieListRemoteMediator")
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    state.pages.size + 1
                }
            }

            Log.d("SAMPLE","MovieListRemoteMediator:page:${loadKey}")
            val nextKey = fetchUseCase.execute(loadKey)
            MediatorResult.Success(
                endOfPaginationReached = nextKey == null
            )
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}