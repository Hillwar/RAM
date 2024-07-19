package com.hillwar.ram.core.network

import android.util.Log
import com.google.gson.Gson
import retrofit2.HttpException
import retrofit2.Response

class RequestHandler(private val gson: Gson) {

    suspend fun <T : Any> handleRequest(
        execute: suspend () -> Response<T>
    ): Request<T> {
        return try {
            val response = execute()
            val body = response.body()

            if (response.isSuccessful && body != null) {
                Request.Success(body)
            } else {
                val errorBody = response.errorBody()?.string()
                val message = gson.fromJson(errorBody, ErrorResponse::class.java)?.error ?: response.message()
                Request.Error(code = response.code(), message = message)
            }
        } catch (e: HttpException) {
            Request.Error(code = e.code(), message = e.message())
        } catch (e: Throwable) {
            Log.e("RequestHandler", "Network error", e)
            Request.Exception(e)
        }
    }
}
