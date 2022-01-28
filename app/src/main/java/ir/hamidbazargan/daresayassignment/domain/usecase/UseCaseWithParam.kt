package ir.hamidbazargan.daresayassignment.domain.usecase

interface UseCaseWithParam<P> {
    suspend fun execute(page: P)
}