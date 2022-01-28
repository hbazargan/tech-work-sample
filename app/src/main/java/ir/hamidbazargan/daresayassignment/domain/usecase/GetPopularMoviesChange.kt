package ir.hamidbazargan.daresayassignment.domain.usecase

import android.util.Log
import ir.hamidbazargan.daresayassignment.domain.entity.MovieEntity
import ir.hamidbazargan.daresayassignment.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPopularMoviesChange(
    private val repository: Repository
) : UseCaseWithReturn<Flow<Boolean>> {
    override suspend fun execute(): Flow<Boolean> =
        repository.getPopularMoviesChange()
}