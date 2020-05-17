package com.inkiu.domain.usecase

import io.reactivex.Observable
import io.reactivex.Single

interface SingleUseCase<P, R> {
    fun execute(param: P): Single<R>
}

interface ObservableUseCase<P, R> {
    fun execute(param: P): Observable<R>
}