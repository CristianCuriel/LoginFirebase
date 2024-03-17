package com.freedomus.project.app.ui.login.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {

    private val _nombre = MutableLiveData("")
    val nombre: LiveData<String> = _nombre

    private val _apellido = MutableLiveData("")
    val apellido: LiveData<String> = _apellido

    private val _contra = MutableLiveData("")
    val contra: LiveData<String> = _contra

    private val _contraConfir = MutableLiveData("")
    val contraConfir: LiveData<String> = _contraConfir

    fun onRegisterChanged(nombre: String, apellido: String, contra: String, contraConfir: String) {
        _nombre.value = nombre
        _apellido.value = apellido
        _contra.value = contra
        _contraConfir.value = contraConfir
    }

}