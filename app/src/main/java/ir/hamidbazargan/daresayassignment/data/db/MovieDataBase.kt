package ir.hamidbazargan.daresayassignment.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.hamidbazargan.daresayassignment.data.db.entity.BookmarkMovieDataBaseEntity
import ir.hamidbazargan.daresayassignment.data.db.entity.PopularMovieDataBaseEntity
import ir.hamidbazargan.daresayassignment.data.db.entity.TopRatedDataBaseEntity

@Database(
    entities = [
        BookmarkMovieDataBaseEntity::class,
        PopularMovieDataBaseEntity::class,
        TopRatedDataBaseEntity::class
    ],
    version = 1
)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun MovieDao(): MovieDao
}