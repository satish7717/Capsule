package com.demo.capsule.utils

import android.content.Context
import android.widget.Toast
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


object UiHelper {
    suspend inline fun <reified T> readJson(
        fileName: String,
        applicationContext: Context
    ): List<T> {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val listType = Types.newParameterizedType(List::class.java, T::class.java)
        val adapter: JsonAdapter<List<T>> = moshi.adapter(listType)
        return adapter.fromJson(
            applicationContext.assets.open("$fileName.json").bufferedReader()
                .use { it.readText() })!!
    }

    fun Context.toast(message: String?, duration: Int = Toast.LENGTH_SHORT) {
        if (!message.isNullOrEmpty())
            Toast.makeText(this, message, duration).show()
    }



}