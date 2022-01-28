package ir.hamidbazargan.daresayassignment.data.mapper

import ir.hamidbazargan.daresayassignment.data.db.entity.BookmarkMovieDataBaseEntity
import ir.hamidbazargan.daresayassignment.data.db.entity.PopularMovieDataBaseEntity
import ir.hamidbazargan.daresayassignment.data.db.entity.TopRatedDataBaseEntity
import ir.hamidbazargan.daresayassignment.data.webservice.reponse.MovieResponse
import ir.hamidbazargan.daresayassignment.domain.entity.MovieEntity

fun PopularMovieDataBaseEntity.toMovieEntity(): MovieEntity = MovieEntity(
    id,
    title,
    originalTitle,
    overview,
    originalLanguage,
    releaseDate,
    adult,
    backdropPath,
    popularity,
    posterPath,
    video,
    voteAverage,
    voteCount,
    page,
    totalPage
)

fun TopRatedDataBaseEntity.toMovieEntity(): MovieEntity = MovieEntity(
    id,
    title,
    originalTitle,
    overview,
    originalLanguage,
    releaseDate,
    adult,
    backdropPath,
    popularity,
    posterPath,
    video,
    voteAverage,
    voteCount,
    page,
    totalPage
)

fun BookmarkMovieDataBaseEntity.toMovieEntity(): MovieEntity = MovieEntity(
    id,
    title,
    originalTitle,
    overview,
    originalLanguage,
    releaseDate,
    adult,
    backdropPath,
    popularity,
    posterPath,
    video,
    voteAverage,
    voteCount,
    page,
    totalPage
)

fun MovieResponse.toMovieEntity(
    page: Int,
    totalPage: Int
): MovieEntity = MovieEntity(
    id,
    title,
    originalTitle,
    overview,
    originalLanguage,
    releaseDate,
    adult,
    backdropPath,
    popularity,
    posterPath,
    video,
    voteAverage,
    voteCount,
    page,
    totalPage
)

fun MovieEntity.toBookmarkMovieDataBaseEntity(): BookmarkMovieDataBaseEntity = BookmarkMovieDataBaseEntity(
    id,
    title,
    originalTitle,
    overview,
    originalLanguage,
    releaseDate,
    adult,
    backdropPath,
    popularity,
    posterPath,
    video,
    voteAverage,
    voteCount,
    1,
    1
)

fun MovieEntity.toPopularMovieDataBaseEntity(): PopularMovieDataBaseEntity = PopularMovieDataBaseEntity(
    id,
    title,
    originalTitle,
    overview,
    originalLanguage,
    releaseDate,
    adult,
    backdropPath,
    popularity,
    posterPath,
    video,
    voteAverage,
    voteCount,
    page,
    totalPage
)

fun MovieEntity.toTopRatedDataBaseEntity(): TopRatedDataBaseEntity = TopRatedDataBaseEntity(
    id,
    title,
    originalTitle,
    overview,
    originalLanguage,
    releaseDate,
    adult,
    backdropPath,
    popularity,
    posterPath,
    video,
    voteAverage,
    voteCount,
    page,
    totalPage
)