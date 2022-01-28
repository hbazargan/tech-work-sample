package ir.hamidbazargan.daresayassignment.data.db

import androidx.room.*
import ir.hamidbazargan.daresayassignment.data.db.entity.BookmarkMovieDataBaseEntity
import ir.hamidbazargan.daresayassignment.data.db.entity.PopularMovieDataBaseEntity
import ir.hamidbazargan.daresayassignment.data.db.entity.TopRatedDataBaseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM bookmarkmovies WHERE id == :id")
    fun queryMovie(id: Int): BookmarkMovieDataBaseEntity?

    @Query("SELECT * FROM bookmarkmovies WHERE page = :page")
    fun queryBookmarkMovies(page: Int): List<BookmarkMovieDataBaseEntity>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    fun insertBookmarkMovie(movieEntities: List<BookmarkMovieDataBaseEntity>)

    @Query("SELECT * FROM popularmovies")
    fun queryPopularMovies(): Flow<List<PopularMovieDataBaseEntity>>

    @Query("SELECT * FROM popularmovies WHERE page = :page")
    fun queryPopularMovies(page: Int): List<PopularMovieDataBaseEntity>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    fun insertPopularMovie(movieEntities: List<PopularMovieDataBaseEntity>)

    @Query("DELETE FROM popularmovies")
    fun clearPopularAll()

    @Query("SELECT * FROM topratedmovies WHERE page = :page")
    fun queryTopRatedMovies(page: Int): List<TopRatedDataBaseEntity>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    fun insertTopRatedMovie(movieEntities: List<TopRatedDataBaseEntity>)

    @Query("DELETE FROM topratedmovies")
    fun clearTopRatedAll()
}