package com.freedomus.project.app.ui.login.verification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freedomus.project.app.domain.SendEmailVerificationUseCase
import kotlinx.coroutines.launch

class VerificationViewModel (): ViewModel(){

    val sendEmailVerificationUseCase =  SendEmailVerificationUseCase()

    init {
        EmailVerification()
    }


    fun EmailVerification() {
        viewModelScope.launch { sendEmailVerificationUseCase() }
    }

}