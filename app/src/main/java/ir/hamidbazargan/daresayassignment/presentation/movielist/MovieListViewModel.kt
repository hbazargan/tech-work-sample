package ir.hamidbazargan.daresayassignment.presentation.movielist

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import ir.hamidbazargan.daresayassignment.domain.entity.MovieEntity
import ir.hamidbazargan.daresayassignment.domain.usecase.UseCaseWithParamReturn
import ir.hamidbazargan.daresayassignment.domain.usecase.UseCaseWithReturn
import ir.hamidbazargan.daresayassignment.presentation.movielist.paging.MovieListPagingSource
import ir.hamidbazargan.daresayassignment.presentation.movielist.paging.MovieListRemoteMediator
import kotlinx.coroutines.flow.Flow

class MovieListViewModel(
    private val fetchUsecase: UseCaseWithParamReturn<Int, Int?>?,
    private val getChangeUsecase: UseCaseWithReturn<Flow<Boolean>>?,
    private val getUsecase: UseCaseWithParamReturn<Int, List<MovieEntity>>
) : ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    val pagerFlow = Pager(
        config = PagingConfig(pageSize = 20),
        remoteMediator = fetchUsecase?.let {
            MovieListRemoteMediator(
                fetchUsecase
            )
        }
    ) {
        MovieListPagingSource(getChangeUsecase, getUsecase)
    }.flow

}