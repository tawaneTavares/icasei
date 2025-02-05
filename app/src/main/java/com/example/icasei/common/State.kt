package com.example.icasei.common

sealed class State<out T> {

    data class Data<T>(val data: T) : State<T>()

    data class Error(val cause: Throwable? = null) : State<Nothing>()

    data object Loading : State<Nothing>()

    data object Idle : State<Nothing>()

    fun isData() = this is Data

    fun isError() = this is Error

    fun isLoading() = this is Loading

    fun isIdle() = this is Idle

    companion object {

        fun <T> data(data: T): State<T> = Data(data)

        fun <T> error(cause: Throwable): State<T> = Error(cause)

        fun <T> loading(): State<T> = Loading

        fun <T> idle(): State<T> = Idle
    }
}
