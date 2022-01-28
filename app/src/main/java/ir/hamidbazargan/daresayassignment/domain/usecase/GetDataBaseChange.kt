package ir.hamidbazargan.daresayassignment.domain.usecase

import android.util.Log
import ir.hamidbazargan.daresayassignment.domain.entity.MovieEntity
import ir.hamidbazargan.daresayassignment.domain.repository.Repository

class GetDataBaseChange(
    private val repository: Repository
) : UseCaseWithParamReturn<Int, List<MovieEntity>> {
    override suspend fun execute(page: Int): List<MovieEntity> {
        Log.d("SAMPLE","GetTopRatedMovies")
        return repository.getTopRatedMovies(page)
    }
}