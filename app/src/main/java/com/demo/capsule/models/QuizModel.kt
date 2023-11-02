package com.demo.capsule.models


import androidx.annotation.Keep

@Keep
data class QuizModel(
    val question: String?,
    val options: List<String?>?,
    val answer: String?
)