package com.example.icasei.common

import kotlinx.coroutines.coroutineScope
import retrofit2.Response

open class BaseRemoteData {

    suspend fun <T : Any> safeCall(exec: suspend () -> T): T {
        return coroutineScope {
            try {
                exec()
            } catch (e: Exception) {
                throw e
            }
        }
    }

    protected inline fun <T> threatResponse(response: Response<T>, exec: (T) -> Unit) {
        val errorBody = response.errorBody()?.string()

        exec(response.body() ?: throw Exception(errorBody))
    }
}
