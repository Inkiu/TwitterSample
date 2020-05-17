package com.inkiu.data.mapper

interface Mapper<P, R> {
    operator fun invoke(src: P) = map(src)
    fun map(src: P): R
}