package com.freedomus.project.app.ui.login.verification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CountdownViewModel : ViewModel() {

    private val _counter = MutableStateFlow(60)
    val counter: StateFlow<Int> = _counter


    private var countdownJob: Job? = null
    private var isCountingDown: Boolean = false

    fun startCountdown() {
        if (!isCountingDown) {
            isCountingDown = true
            countdownJob = viewModelScope.launch {
                withContext(Dispatchers.Default) {
                    for (i in 60 downTo 0) {
                        _counter.value = i
                        delay(1000)
                    }
                    isCountingDown = false
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        countdownJob?.cancel()
    }
}
