package com.demo.capsule.base

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @field:Json(name = "message")
    val message: String? = "",
    @field:Json(name = "statusCode")
    val statusCode: Int? = 0,
)
