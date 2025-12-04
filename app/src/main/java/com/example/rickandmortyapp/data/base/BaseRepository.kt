package com.example.rickandmortyapp.data.base

import com.example.rickandmortyapp.core.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseRepository {

    protected fun <T, D> doRequest(
        request: suspend () -> Response<T>,
        result: (T) -> D
      ): Flow<Either<String, D>> {
           return flow {
            delay(1000)
            try {
                val response = request()
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let {dto ->
                        emit(Either.Right(result(dto)))
                    }
                } else {
                    emit(Either.Left(response.message().ifEmpty { "Unknown error" }))
                }
            } catch (e: UnknownHostException) {
                emit(Either.Left(e.localizedMessage ?: "Unknown message"))
            } catch (e: SocketTimeoutException) {
                emit(Either.Left(e.localizedMessage ?: "Unknown message"))
            }
        }.flowOn(Dispatchers.IO)
    }
}
