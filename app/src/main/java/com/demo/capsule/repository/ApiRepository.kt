package com.demo.capsule.repository

import android.content.Context
import com.demo.capsule.base.BaseApiResponse
import com.demo.capsule.models.QuizModel
import com.demo.capsule.utils.UiHelper
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


@ActivityRetainedScoped
class ApiRepository @Inject constructor(
) : BaseApiResponse() {

    suspend fun getQuestions(fileName: String, context: Context): Flow<List<QuizModel>> {
        return flow {
            emit(UiHelper.readJson<QuizModel>(fileName, context))
        }.flowOn(Dispatchers.IO)
    }


}
