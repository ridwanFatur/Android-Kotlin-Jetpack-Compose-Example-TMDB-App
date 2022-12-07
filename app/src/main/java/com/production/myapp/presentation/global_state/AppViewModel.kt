package com.production.myapp.presentation.global_state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor() : ViewModel() {
    var valueState by mutableStateOf(0)
        private set

    init {

    }

    fun addValueState() {
        valueState ++
    }

    fun minValueState() {
        valueState --
    }

}