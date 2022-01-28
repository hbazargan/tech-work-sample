package ir.hamidbazargan.daresayassignment.domain.usecase

import android.util.Log
import ir.hamidbazargan.daresayassignment.domain.entity.MovieEntity
import ir.hamidbazargan.daresayassignment.domain.repository.Repository

class GetMovie(
    private val repository: Repository
) : UseCaseWithParamReturn<Int, List<MovieEntity>> {
    override suspend fun execute(id: Int): List<MovieEntity> {
        Log.d("SAMPLE","GetMovie")
        return listOf(repository.getMovie(id))
    }
}