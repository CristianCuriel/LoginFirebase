package com.freedomus.project.app.ui.login.register

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        Log.i("Cris", "Nombre esta vacio: ${isValidName(_nombre.value!!)}")
        Log.i("Cris", "Email verificado: ${isValidOrEmptyEmail(_correo.value!!)}")
        Log.i(
            "Cris",
            "Contraseña verificado: ${
                isValidOrEmptyPassword(
                    _contra.value!!,
                    _contraConfir.value!!
                )
            }"
        )

        if (isValidName(_nombre.value!!) && isValidOrEmptyEmail(_correo.value!!) && isValidOrEmptyPassword(
                _contra.value!!,
                _contraConfir.value!!
            )
        ) {
            viewModelScope.launch {
                val accountCreated = createAccountUseCase(
                    UserSignIn(
                        nombre = _nombre.value!!,
                        apellido = _apellido.value!!,
                        correo = _correo.value!!,
                        contrasena = _contra.value!!,
                        contrasenaConfirmada = _contraConfir.value!!
                    )
                )

                if(accountCreated){
                    _navigateVerificationEmail.value = true
                    Log.i("Cris", "Vamos a verificar el email!!")
                }else{
                    Log.i("Cris", "Error al crear el usuario")
                }
            }

        }else{
            Log.i("Cris", "Verifique los datos de entrada.")
        }


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

}