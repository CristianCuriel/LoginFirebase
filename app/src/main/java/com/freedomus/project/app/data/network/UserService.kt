package com.freedomus.project.app.data.network

import com.freedomus.project.app.ui.login.register.model.UserSignIn
import kotlinx.coroutines.tasks.await

class UserService {

    private val firebase = FirebaseClient()

    companion object {
        const val USER_COLLECTION = "users"
    }

    suspend fun createUserTable(userSignIn: UserSignIn) = runCatching {

        val user = hashMapOf(
            "nombre" to userSignIn.nombre,
            "apellido" to userSignIn.apellido,
            "correo" to userSignIn.correo
        )

        firebase.db
            .collection(USER_COLLECTION)
            .add(user).await()

    }.isSuccess

}