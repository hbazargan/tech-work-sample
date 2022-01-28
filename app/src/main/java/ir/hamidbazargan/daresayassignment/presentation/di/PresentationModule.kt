package ir.hamidbazargan.daresayassignment.presentation.di

import ir.hamidbazargan.daresayassignment.presentation.pages.detail.DetailViewmodel
import ir.hamidbazargan.daresayassignment.presentation.movielist.MovieListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


val coroutineDispatcherModule = module {
    single { Dispatchers.IO }
}

val viewModelModule = module {

    viewModel(named("detail")) {
        DetailViewmodel(
            coroutineDispatcher = get(),
            getMovie = get(named("GetMovie")),
            saveMovie = get())
    }

    viewModel(named("popular")) {
        MovieListViewModel(
            fetchUsecase = get(named("FetchPopularMovies")),
            getChangeUsecase = get(named("GetPopularMoviesChange")),
            getUsecase = get(named("GetPopularMovies"))
        )
    }

    viewModel(named("topRated")) {
        MovieListViewModel(
            fetchUsecase = get(named("FetchTopRatedMovies")),
            getChangeUsecase = null,
            getUsecase = get(named("GetTopRatedMovies"))
        )
    }

    viewModel(named("bookmark")) {
        MovieListViewModel(
            fetchUsecase = null,
            getChangeUsecase = null,
            getUsecase = get(named("GetBookmarkMovies"))
        )
    }
}