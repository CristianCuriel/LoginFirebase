package com.freedomus.project.app.data.response

sealed class LoginResult {

    object Loading: LoginResult()
    data class Error(val exception: Throwable): LoginResult()
    data class Success(val verified: Boolean) : LoginResult()
}