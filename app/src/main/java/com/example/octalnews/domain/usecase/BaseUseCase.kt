package com.example.octalnews.domain.usecase

abstract class BaseUseCase<T> {
    abstract suspend fun invoke(value1: Any? = null, value2: Any? = null): T
}