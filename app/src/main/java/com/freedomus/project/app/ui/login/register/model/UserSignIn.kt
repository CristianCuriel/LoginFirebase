package com.freedomus.project.app.ui.login.register.model

data class UserSignIn(
    val nombre: String = "",
    val apellido: String = "" ,
    val correo: String = "" ,
    val contrasena: String = "" ,
    val contrasenaConfirmada: String = ""
)
