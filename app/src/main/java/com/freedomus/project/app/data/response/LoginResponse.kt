package com.freedomus.project.app.data.response

sealed class LoginResult {

    object Initial: LoginResult()
    data class Error(val exception: String): LoginResult()
    data class Success(val verified: Boolean) : LoginResult()
}