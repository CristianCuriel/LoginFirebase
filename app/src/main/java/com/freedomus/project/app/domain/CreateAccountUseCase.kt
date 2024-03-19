package com.freedomus.project.app.domain

import com.freedomus.project.app.data.network.AuthenticationService
import com.freedomus.project.app.data.network.UserService
import com.freedomus.project.app.data.response.RegisterResponse
import com.freedomus.project.app.ui.login.register.model.UserSignIn
import kotlinx.coroutines.flow.Flow

class CreateAccountUseCase {

    private val authenticationService = AuthenticationService()
    private val userService = UserService()

    suspend operator fun invoke(userSignIn: UserSignIn): Flow<RegisterResponse> =
            authenticationService.createAccount(userSignIn.correo, userSignIn.contrasena)



}