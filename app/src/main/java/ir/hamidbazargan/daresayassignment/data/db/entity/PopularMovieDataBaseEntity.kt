package ir.hamidbazargan.daresayassignment.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popularmovies")
data class PopularMovieDataBaseEntity(
    @PrimaryKey val id: Int,
    var title: String,
    var originalTitle: String,
    var overview: String,
    var originalLanguage: String,
    var releaseDate: String?,
    var adult: Boolean,
    var backdropPath: String?,
    var popularity: Float,
    var posterPath: String?,
    var video: Boolean,
    var voteAverage: Float,
    var voteCount: Int,
    var page: Int,
    var totalPage: Int
)
