package com.freedomus.project.app.data.response

sealed class RegisterResponse {
    data class Success(val isRegistered: Boolean) : RegisterResponse()
    data class Error(val message: String) : RegisterResponse()
}

