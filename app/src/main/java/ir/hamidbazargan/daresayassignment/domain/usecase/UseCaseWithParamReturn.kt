package ir.hamidbazargan.daresayassignment.domain.usecase

interface UseCaseWithParamReturn<P, R> {
    suspend fun execute(param: P): R
}