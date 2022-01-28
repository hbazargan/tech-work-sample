package ir.hamidbazargan.daresayassignment.domain.usecase

import android.util.Log
import ir.hamidbazargan.daresayassignment.domain.entity.MovieEntity
import ir.hamidbazargan.daresayassignment.domain.repository.Repository

class SaveMovie(
    private val repository: Repository
) : UseCaseWithParam<MovieEntity> {
    override suspend fun execute(movieEntity: MovieEntity) {
        Log.d("SAMPLE","SaveMovie")
        return repository.saveMovie(movieEntity)
    }
}