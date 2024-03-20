package com.freedomus.project.app.ui.login.verification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freedomus.project.app.domain.SendEmailVerificationUseCase
import com.freedomus.project.app.domain.VerifyEmailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VerificationViewModel() : ViewModel() {

    val sendEmailVerificationUseCase = SendEmailVerificationUseCase()
    val verifyEmailUseCase = VerifyEmailUseCase()

    private val _navigateLogin = MutableStateFlow(false)
    val navigateLogin: StateFlow<Boolean> = _navigateLogin

    init {
        if (!_navigateLogin.value) {
            EmailVerification()
        }
    }


    fun EmailVerification() {
        viewModelScope.launch { sendEmailVerificationUseCase() }
        viewModelScope.launch {
            verifyEmailUseCase().collect {
                _navigateLogin.value = it

            }
        }
    }

}