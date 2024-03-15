package com.freedomus.project.app.domain

import com.freedomus.project.app.data.network.AuthenticationService
import com.freedomus.project.app.data.response.LoginResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class UserInfoProfile {

    private val authenticationService = AuthenticationService()

    operator fun invoke (): FirebaseUser? =
        authenticationService.userInfoProfile()

    fun checkSession(): FirebaseAuth = authenticationService.checkSession()

}