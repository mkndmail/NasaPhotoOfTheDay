package com.mkndmail.nasaphotooftheday.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkndmail.nasaphotooftheday.network.ApodDataRepository
import com.mkndmail.nasaphotooftheday.network.ApodDataResponse
import com.mkndmail.nasaphotooftheday.network.Status
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Mukund, mkndmail@gmail.com on 22, July, 2020
 */

class MainViewModel : ViewModel() {
    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status>
        get() = _status
    private val _apodDataResponse = MutableLiveData<ApodDataResponse?>()
    val apodDataResponse: LiveData<ApodDataResponse?>
        get() = _apodDataResponse
    private val _startNewActivity = MutableLiveData<ApodDataResponse>()
    val startNewActivity: LiveData<ApodDataResponse>
        get() = _startNewActivity
    private val _opendatePicker = MutableLiveData<Boolean>()
    val openDatePicker: LiveData<Boolean>
        get() = _opendatePicker
    private val repository by lazy {
        ApodDataRepository()
    }

    init {
        getInitialData()
    }

    fun getInitialData() {
        viewModelScope.launch {
            _status.value = Status.LOADING
            val response = repository.getApodData()
            if (response.error == null) {
                _status.value = Status.SUCCESS
                _apodDataResponse.value = response
            } else {
                _status.value = Status.ERROR
                _apodDataResponse.value = null
            }
        }
    }

    fun setAction(apodDataResponse: ApodDataResponse?) {
        apodDataResponse?.let {
            _startNewActivity.value = it
        }
    }

    fun openDatePicker() {
        _opendatePicker.value = true
    }

    fun getApodDataByDate(requiredDate: String) {
        viewModelScope.launch {
            _status.value = Status.LOADING
            val response = repository.getApodDataByDate(requiredDate)
            if (response.error == null) {
                _status.value = Status.SUCCESS
                _apodDataResponse.value = response
            } else {
                _status.value = Status.ERROR
                _apodDataResponse.value = null
            }
        }

    }


    @SuppressLint("SimpleDateFormat")
    fun getDateInYYYYMMDD(year: Int, month: Int, dayOfMonth: Int): String {
        val cal: Calendar = Calendar.getInstance()
        cal.set(year, month, dayOfMonth)
        val format = "yyyy-MM-dd"
        return SimpleDateFormat(format).format(cal.time)
    }
}
