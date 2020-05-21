package com.inkiu.domain.usecase

interface SingleUseCase<P, R> {
    suspend fun execute(param: P): R
    suspend operator fun invoke(param: P): Result<R> {
        return runCatching { execute(param) }
    }
}