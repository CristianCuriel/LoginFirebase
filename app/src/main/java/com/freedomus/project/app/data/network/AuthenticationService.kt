package com.freedomus.project.app.data.network

import com.freedomus.project.app.data.model.UserProfile
import com.freedomus.project.app.data.response.LoginResult
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class AuthenticationService {

    private val firebase = FirebaseClient()

    fun userInfoProfile():FirebaseUser? = firebase.auth.currentUser


    suspend fun login(email: String, password: String): LoginResult = runCatching {
        firebase.auth.signInWithEmailAndPassword(email, password).await()
    }.toLoginResult()

    fun signOutUser() = runCatching {
        firebase.auth.signOut()
    }.isSuccess

    suspend fun sendVerificationEmail() = runCatching {
        firebase.auth.currentUser?.sendEmailVerification()?.await()
    }.isSuccess

   private fun Result<AuthResult>.toLoginResult() = when (val result = getOrNull()) {
        null -> LoginResult.Error
        else -> {
            val userId = result.user
            checkNotNull(userId)
            LoginResult.Success(result.user?.isEmailVerified ?: false)
        }
    }



}