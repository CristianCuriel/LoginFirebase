package com.freedomus.project.app.domain

import com.freedomus.project.app.data.network.AuthenticationService

class SignOutUserUseCase {
    private val authenticationService =  AuthenticationService()
    operator fun invoke() = authenticationService.signOutUser()
}