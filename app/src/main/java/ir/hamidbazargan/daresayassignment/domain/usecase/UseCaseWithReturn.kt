package ir.hamidbazargan.daresayassignment.domain.usecase

interface UseCaseWithReturn<R> {
    suspend fun execute(): R
}