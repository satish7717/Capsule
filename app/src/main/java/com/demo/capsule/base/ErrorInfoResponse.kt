package com.demo.capsule.base

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorInfoResponse(
    @field:Json(name = "error")
    val error: String?,
    @field:Json(name = "message")
    val message: String? = "",
    @field:Json(name = "error_description")
    val error_description: String?
)
