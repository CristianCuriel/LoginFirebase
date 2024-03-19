package com.freedomus.project.app.data.network

import com.freedomus.project.app.data.response.LoginResult
import com.freedomus.project.app.data.response.RegisterResponse
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class AuthenticationService {

    private val firebase = FirebaseClient()

    fun userInfoProfile(): FirebaseUser? = firebase.auth.currentUser


    suspend fun login(email: String, password: String): LoginResult = runCatching {
        firebase.auth.signInWithEmailAndPassword(email, password).await()
    }.toLoginResult()

    suspend fun createAccount(email: String, password: String): Flow<RegisterResponse> {
        return flow {
            try {
                firebase.auth.createUserWithEmailAndPassword(email, password).await()
                emit(RegisterResponse.Success(true))
            } catch (e: FirebaseAuthException) {
                when (e.errorCode) {

                    "ERROR_EMAIL_ALREADY_IN_USE" -> emit(
                        RegisterResponse.Error(
                            "El correo electrónico ya está en uso. Por favor intenta con otro diferente!"
                        )
                    )

                    "ERROR_NETWORK_REQUEST_FAILED" -> emit(RegisterResponse.Error(
                            "Fallo la solicitud a la red, por favor revise la conexion a internet y vuelva a intentarlo!"
                    ))

                    "ERROR_INVALID_EMAIL" -> emit(RegisterResponse.Error(
                        "Revisa el correo electronico. No es un correo valido"
                    ))

                    else -> emit(RegisterResponse.Error("Mensaje: ${e.message}  Codigo: ${e.errorCode}"))
                }
            } catch (e: FirebaseNetworkException){
                emit(RegisterResponse.Error("Parece que hay un problema con la red, por favor intentalo de nuevo mas tarde. Error: ${e.message}"))
            }
        }

    }


    fun signOutUser() = runCatching {
        firebase.auth.signOut()
    }.isSuccess

    fun checkSession(): FirebaseAuth = firebase.auth


    suspend fun sendVerificationEmail() = runCatching {
        firebase.auth.currentUser?.sendEmailVerification()?.await()
    }.isSuccess


    private fun Result<AuthResult>.toLoginResult() = when (val result = getOrNull()) {
        null -> LoginResult.Error(exception = exceptionOrNull()!!)
        else -> {
            val userId = result.user
            checkNotNull(userId)
            LoginResult.Success(result.user?.isEmailVerified ?: false)
        }
    }



}