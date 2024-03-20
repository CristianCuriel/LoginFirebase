package com.freedomus.project.app.ui.login.register

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freedomus.project.app.data.response.RegisterResponse
import com.freedomus.project.app.domain.CreateAccountUseCase
import com.freedomus.project.app.ui.login.register.model.UserSignIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val createAccountUseCase = CreateAccountUseCase()

    private val _nombre = MutableLiveData("")
    val nombre: LiveData<String> = _nombre

    private val _apellido = MutableLiveData("")
    val apellido: LiveData<String> = _apellido

    private val _contra = MutableLiveData("")
    val contra: LiveData<String> = _contra

    private val _contraConfir = MutableLiveData("")
    val contraConfir: LiveData<String> = _contraConfir

    private val _correo = MutableLiveData("")
    val correo: LiveData<String> = _correo

    private val _navigateVerificationEmail = MutableStateFlow(false)
    val navigateVerificationEmail: StateFlow<Boolean> = _navigateVerificationEmail

    private val _ShowDialogError = MutableStateFlow(false)
    val ShowDialogError: StateFlow<Boolean> = _ShowDialogError

    private val _viewStateVerifi = MutableStateFlow(StateDataRegister())
    val viewStateVerifi: StateFlow<StateDataRegister> = _viewStateVerifi

    private val _textErrorRegister = MutableLiveData("")
    val textErrorRegister: LiveData<String> = _textErrorRegister

    fun onRegisterChanged(
        nombre: String,
        apellido: String,
        correo: String,
        contra: String,
        contraConfir: String,
    ) {
        _nombre.value = nombre
        _apellido.value = apellido
        _contra.value = contra
        _correo.value = correo
        _contraConfir.value = contraConfir
    }

    fun registerUser() {

        val U = UserSignIn(
            nombre = _nombre.value!!,
            apellido = _apellido.value!!,
            correo = _correo.value!!,
            contrasena = _contra.value!!,
            contrasenaConfirmada = _contraConfir.value!!
        )

        if (isValidName(U.nombre) && isValidOrEmptyEmail(U.correo) && isValidOrEmptyPassword(
                U.contrasena, U.contrasenaConfirmada )
        ) {
            viewModelScope.launch {
                _viewStateVerifi.value = StateDataRegister( isLoading = true)
                val accountCreated = createAccountUseCase(U)

                accountCreated.collect { C ->
                    when (C) {
                        is RegisterResponse.Error -> {
                            _ShowDialogError.value = true
                            _textErrorRegister.value = C.message
                            Log.i("Cris", C.message)
                        }

                        is RegisterResponse.Success -> {
                            createAccountUseCase.saveAccountInfoCreated(U)
                            _navigateVerificationEmail.value = true
                        }
                    }
                }
                _viewStateVerifi.value = StateDataRegister( isLoading = false)
            }

        } else {
            onFieldsChanged(U)
        }


    }

    fun onDimissDialogError(){
        _ShowDialogError.value = !_ShowDialogError.value
    }

    private fun onFieldsChanged(U: UserSignIn) {
        _viewStateVerifi.value = StateDataRegister(
            isValidContra = validarContrasena(U.contrasena),
            isValidContraConfir = isValidOrEmptyPassword(U.contrasena, U.contrasenaConfirmada),
            isValidEmail = isValidOrEmptyEmail(U.correo),
            )
    }


    private fun isValidName(name: String): Boolean =
        name.length >= 6 && name.isNotEmpty()

    private fun isValidOrEmptyEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty()


    private fun isValidOrEmptyPassword(contra: String, contraConfir: String): Boolean =
        (validarContrasena(contra) && contra == contraConfir) && contra.isNotEmpty() && contraConfir.isNotEmpty()

    private fun validarContrasena(contra: String): Boolean {
        val regex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}\$")
        return regex.matches(contra)
    }

    fun changedStateNavigateVerification() {
        _navigateVerificationEmail.value = false
    }

}