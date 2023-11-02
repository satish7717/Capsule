package com.demo.capsule.base

import android.text.TextUtils
import com.demo.capsule.utils.NetworkResult
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import retrofit2.Response


abstract class BaseApiResponse {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body)
                }
            }
            val moshi = Moshi.Builder().build()
            val jsonAdapter: JsonAdapter<ErrorInfoResponse> =
                moshi.adapter<ErrorInfoResponse>(ErrorInfoResponse::class.java)
            val jsonObject = response.errorBody()?.string()?.let { jsonAdapter.fromJson(it) }
            val desc = jsonObject?.error_description
            val message = if (!TextUtils.isEmpty(desc)) desc else jsonObject?.message
            return error(message.toString())
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error(errorMessage)

}