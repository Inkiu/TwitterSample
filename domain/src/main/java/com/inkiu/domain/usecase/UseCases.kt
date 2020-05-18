package com.inkiu.domain.usecase

interface SingleUseCase<P, R> {
    suspend fun execute(param: P): R
}