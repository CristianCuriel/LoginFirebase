package com.freedomus.project.app.ui.login.login

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

class LoginViewModel : ViewModel() {

    private val loginUseCase = LoginUseCase()
    //private val userInfoProfile = UserInfoProfile()

    private val _viewStateVerifi = MutableStateFlow(LoginViewState())
    val viewStateVerifi: StateFlow<LoginViewState> = _viewStateVerifi

    private val _ShowDialogError = MutableStateFlow(false)
    val ShowDialogError: StateFlow<Boolean> = _ShowDialogError

    private val _textErrorRegister = MutableLiveData("")
    val textErrorRegister: LiveData<String> = _textErrorRegister

    private val _navigateHome = MutableStateFlow(false)
    val navigateHome: StateFlow<Boolean> = _navigateHome

    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password


    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
    }


    fun onLoginSelected() {
        if (isValidEmail(email.value!!) && isValidPassword(password.value!!)) {
            loginUser(email.value!!, password.value!!)
        } else {
            onFieldsChanged(email.value!!, password.value!!)
        }
    }

    private fun onFieldsChanged(email: String, password: String) {
        _viewStateVerifi.value = LoginViewState(
            isValidEmail = isValidEmail(email),
            isValidPassword = isValidPassword(password)
        )
    }

    private fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            _viewStateVerifi.value = LoginViewState(isLoading = true)
            loginUseCase(email, password).collect { u ->
                when (u) {
                    is LoginResult.Error -> {
                        _ShowDialogError.value = true
                        _textErrorRegister.value = u.exception
                        Log.i("Cris", u.exception)
                    }

                    LoginResult.Initial -> {
                        _navigateHome.value = false
                    }

                    is LoginResult.Success -> {
                        _navigateHome.value = true
                    }
                }

            }
            _viewStateVerifi.value = LoginViewState(isLoading = false)
        }
    }

    fun resetStateUI() {
        _navigateHome.value = false
    }

    fun onDimissDialogError(){
        _ShowDialogError.value = !_ShowDialogError.value
    }

    private fun isValidEmail(email: String): Boolean {
        val b = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        return b && email.isNotEmpty()
    }

    private fun isValidPassword(password: String): Boolean {
        val regex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}\$")
        return regex.matches(password)
    }
}