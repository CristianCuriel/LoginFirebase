package com.freedomus.project.app.domain

import com.freedomus.project.app.data.network.AuthenticationService


class SendEmailVerificationUseCase () {
    private val authenticationService =  AuthenticationService()
    suspend operator fun invoke() = authenticationService.sendVerificationEmail()
}