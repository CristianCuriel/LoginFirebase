package com.freedomus.project.app.ui.login

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freedomus.project.app.data.response.LoginResult
import com.freedomus.project.app.domain.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    private val loginUseCase = LoginUseCase()

    private companion object {
        const val MIN_PASSWORD_LENGTH = 6
    }

    private val _viewState = MutableStateFlow(LoginViewState())
    val viewState:StateFlow<LoginViewState> = _viewState

    private val _navigateToVerifyAccount = MutableStateFlow(true)
    val navigateToVerifyAccount: StateFlow<Boolean> = _navigateToVerifyAccount

    private val _email = MutableLiveData("")
    val email : LiveData<String> = _email

    private val _password = MutableLiveData("")
    val password : LiveData<String> = _password

    fun onLoginChanged(email:String, password:String){
        _email.value = email
        _password.value = password
    }

    fun changedNavigateToVerifyAccount(b:Boolean){
        _navigateToVerifyAccount.value = b
    }
    fun onLoginSelected() {
        if (isValidEmail(email.value!!) && isValidPassword(password.value!!)) {
            loginUser(email.value!!, password.value!!)
        } else {
            onFieldsChanged(email.value!!, password.value!!)
        }
    }

    fun onFieldsChanged(email: String, password: String) {
        _viewState.value = LoginViewState(
            isValidEmail = isValidEmail(email),
            isValidPassword = isValidPassword(password)
        )
    }

    private fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            _viewState.value = LoginViewState(isLoading = true)
            when (val result = loginUseCase(email, password)) {
                LoginResult.Error -> {
                    Log.i("Cris","Error al iniciar sesion")
                    _viewState.value = LoginViewState(isLoading = false)
                }
                is LoginResult.Success -> {
                    if (result.verified) {
                        Log.i("Cris","Usuario verificado")
                    } else {
                        Log.i("Cris","Usuario NO verificado")
                        changedNavigateToVerifyAccount(false)
                    }
                }
            }
            _viewState.value = LoginViewState(isLoading = false)
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val b = Patterns.EMAIL_ADDRESS.matcher(email).matches()
       return b && email.isNotEmpty()
    }

    private fun isValidPassword(password: String): Boolean {
        val b = password.length >= MIN_PASSWORD_LENGTH
        return b && password.isNotEmpty()
    }
}