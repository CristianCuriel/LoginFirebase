package com.freedomus.project.app.ui.home


import android.util.Log
import androidx.lifecycle.ViewModel
import com.freedomus.project.app.domain.SignOutUserUseCase
import com.freedomus.project.app.domain.UserInfoProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class HomeViewModel : ViewModel() {

    private val userInfoProfile = UserInfoProfile()

    private val signOutUserUseCase = SignOutUserUseCase()


    private val _backToNavigateLogin = MutableStateFlow(false)
    val backToNavigateLogin: StateFlow<Boolean> = _backToNavigateLogin

    fun userinfoProfile() = userInfoProfile()

    fun signOutUser() {
        if (signOutUserUseCase()){
            Log.i("Cris", "${_backToNavigateLogin.value}")
            Log.i("Cris", "Sesion cerrada con exito")
        }else{
            Log.i("Cris", "Problemas cerrando la sesion")
        }
    }


}