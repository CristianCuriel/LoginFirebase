package com.freedomus.project.app.domain

import com.freedomus.project.app.data.network.AuthenticationService
import com.freedomus.project.app.data.network.UserService
import com.freedomus.project.app.ui.login.register.model.UserSignIn

class CreateAccountUseCase {

    private val authenticationService = AuthenticationService()
    private val userService = UserService()

    suspend operator fun invoke(userSignIn: UserSignIn): Boolean {
        val accountCreated =
            authenticationService.createAccount(userSignIn.correo, userSignIn.contrasena) != null
        return if (accountCreated) {
            userService.createUserTable(userSignIn)
        } else {
            false
        }
    }

}