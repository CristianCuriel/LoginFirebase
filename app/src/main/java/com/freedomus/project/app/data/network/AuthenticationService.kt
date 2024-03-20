package com.freedomus.project.app.data.network

import com.freedomus.project.app.data.response.LoginResult
import com.freedomus.project.app.data.response.RegisterResponse
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class AuthenticationService {

    private val firebase = FirebaseClient()

    fun userInfoProfile(): FirebaseUser? = firebase.auth.currentUser

    val verifiedAccount: Flow<Boolean> = flow {
        while (true) {
            val verified = verifyEmailIsVerified()
            emit(verified)
            if(verified){
                break
            }else{
                delay(1000)
            }
        }
    }


    suspend fun loginAccount(email: String, password: String): Flow<LoginResult> {
        return flow {
            try {
                firebase.auth.signInWithEmailAndPassword(email, password).await()
                emit(LoginResult.Success(true))
            } catch (e: FirebaseAuthException) {
                when (e.errorCode) {

                    "ERROR_USER_NOT_FOUND" -> emit(LoginResult.Error(
                        "El usuario con el que intenta acceder no existe o no esta registrado. Error: ${e.message}"
                    ))

                    "ERROR_INVALID_CREDENTIAL" -> emit(LoginResult.Error(
                        "El correo electrónico o la contraseña es incorrecta. Error: ${e.message}"
                    ))

                    "ERROR_NETWORK_REQUEST_FAILED" -> emit(LoginResult.Error(
                        "Fallo la solicitud a la red, por favor revise la conexion a internet y vuelva a intentarlo!"
                    ))

                    "ERROR_INVALID_EMAIL" -> emit(LoginResult.Error(
                        "Revisa el correo electronico. No es un correo valido"
                    ))

                    else -> emit(LoginResult.Error("Mensaje: ${e.message}  Codigo: ${e.errorCode}"))
                }
            } catch (e: FirebaseNetworkException){
                emit(LoginResult.Error("Parece que hay un problema con la red, por favor intentalo de nuevo mas tarde. Error: ${e.message}"))
            }
        }

    }

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

    private suspend fun verifyEmailIsVerified(): Boolean {
        firebase.auth.currentUser?.reload()?.await()
        return firebase.auth.currentUser?.isEmailVerified ?: false
    }

    fun signOutUser() = runCatching {
        firebase.auth.signOut()
    }.isSuccess

    fun checkSession(): FirebaseAuth = firebase.auth


    suspend fun sendVerificationEmail() = runCatching {
        firebase.auth.currentUser?.sendEmailVerification()?.await()
    }.isSuccess



}