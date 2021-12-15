package com.android.retrofit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.entity.ResponseUserModelDetails
import com.android.entity.UserSignInRequestModel
import com.android.entity.UserSignInResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(val repo: Repo) : ViewModel() {


    private val _responseLiveData: MutableLiveData<UserSignInResponseModel> = MutableLiveData()
    val responseLiveData: LiveData<UserSignInResponseModel> = _responseLiveData

    fun responseModel(request: UserSignInRequestModel) {

        viewModelScope.launch(Dispatchers.IO) {
            val response = repo.signIn(request)
            response?.let {
                _responseLiveData.postValue(it)
                Log.d("xxx", "$it")
            }

        }
    }


    private val _responseDetailsLiveData: MutableLiveData<ResponseUserModelDetails> =
        MutableLiveData()
    val responseDetailsLiveData: LiveData<ResponseUserModelDetails> = _responseDetailsLiveData

    fun responseDetailsModel() {
        viewModelScope.launch(Dispatchers.IO) {
            val responseDetails = repo.getDetails()
            responseDetails.let {
                _responseDetailsLiveData.postValue(it)
            }
        }
    }
}
