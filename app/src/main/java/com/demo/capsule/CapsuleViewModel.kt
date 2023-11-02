package com.demo.capsule

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.capsule.models.QuizModel
import com.demo.capsule.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CapsuleViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {

    private val _questionsResponse: MutableLiveData<List<QuizModel>> =
        MutableLiveData()
    val questionsResponse: LiveData<List<QuizModel>> = _questionsResponse

    fun questions(fileName: String, context: Context) =
        viewModelScope.launch {
            apiRepository.getQuestions(fileName, context).collect { values ->
                _questionsResponse.value = values
            }
        }


}