package ir.hamidbazargan.daresayassignment.domain.di

import ir.hamidbazargan.daresayassignment.domain.entity.MovieEntity
import ir.hamidbazargan.daresayassignment.domain.usecase.*
import kotlinx.coroutines.flow.Flow
import org.koin.core.qualifier.named
import org.koin.dsl.module

val useCasesModule = module {

    single<UseCaseWithParamReturn<Int, Int?>>(named("FetchPopularMovies"))
    { FetchPopularMovies(repository = get()) }

    single<UseCaseWithParamReturn<Int, Int?>>(named("FetchTopRatedMovies"))
    { FetchTopRatedMovies(repository = get()) }

    single<UseCaseWithParamReturn<Int, List<MovieEntity>>>(named("GetMovie"))
    { GetMovie(repository = get()) }

    single<UseCaseWithParamReturn<Int, List<MovieEntity>>>(named("GetBookmarkMovies"))
    { GetBookmarkMovies(repository = get()) }

    single<UseCaseWithParamReturn<Int, List<MovieEntity>>>(named("GetPopularMovies"))
    { GetPopularMovies(repository = get()) }

    single<UseCaseWithReturn<Flow<Boolean>>>(named("GetPopularMoviesChange"))
    { GetPopularMoviesChange(repository = get()) }

    single<UseCaseWithParamReturn<Int, List<MovieEntity>>>(named("GetTopRatedMovies"))
    { GetTopRatedMovies(repository = get()) }

    single<UseCaseWithParam<MovieEntity>>
    { SaveMovie(repository = get()) }
}