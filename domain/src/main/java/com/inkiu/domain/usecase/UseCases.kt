package com.inkiu.domain.usecase

import io.reactivex.Observable
import io.reactivex.Single

interface SingleUseCase<P, R> {
    suspend fun execute(param: P): R
}