package com.freedomus.project.app.domain

import com.freedomus.project.app.data.network.AuthenticationService
import com.freedomus.project.app.data.response.LoginResult

class LoginUseCase {

    private val authenticationService = AuthenticationService()

    suspend operator fun invoke(email: String, password: String): LoginResult =
        authenticationService.login(email, password)

}