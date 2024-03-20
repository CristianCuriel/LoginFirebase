package com.freedomus.project.app.domain

import com.freedomus.project.app.data.network.AuthenticationService
import kotlinx.coroutines.flow.Flow

class VerifyEmailUseCase {

    private val authenticationService =  AuthenticationService()

    operator fun invoke(): Flow<Boolean> = authenticationService.verifiedAccount

}