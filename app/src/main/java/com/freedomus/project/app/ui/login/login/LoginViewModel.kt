package com.freedomus.project.app.ui.login.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freedomus.project.app.data.response.LoginResult
import com.freedomus.project.app.domain.LoginUseCase
import com.freedomus.project.app.domain.UserInfoProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val loginUseCase = LoginUseCase()
    private val userInfoProfile = UserInfoProfile()

    private companion object {
        const val MIN_PASSWORD_LENGTH = 6
    }

    private val _viewLoginState = MutableStateFlow<LoginResult>(LoginResult.Loading)
    val viewLoginState: StateFlow<LoginResult> = _viewLoginState

    private val _viewStateVerifi = MutableStateFlow(LoginViewState())
    val viewStateVerifi: StateFlow<LoginViewState> = _viewStateVerifi

    private val _isSessionActive = MutableStateFlow(false)
    val isSessionActive: StateFlow<Boolean> = _isSessionActive


    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password


    private fun checkSessionUser(){
        userInfoProfile.checkSession().addAuthStateListener {
            if (it.currentUser !=null)  _isSessionActive.value = true else _isSessionActive.value = false
        }
    }

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
             _viewLoginState.value = loginUseCase(email, password)
            _viewStateVerifi.value = LoginViewState(isLoading = false)
        }
    }

    fun resetStateUI() {
        _viewLoginState.value = LoginResult.Loading
    }



    /*    private fun loginUser(email: String, password: String) {
            viewModelScope.launch {
                _viewState.value = LoginViewState(isLoading = true)
                when (val result = loginUseCase(email, password)) {
                    LoginResult.Error -> {
                        Log.i("Cris","Error al iniciar sesion ${ LoginResult.Error}")
                        _viewState.value = LoginViewState(isLoading = false)
                    }
                    is LoginResult.Success -> {
                        if (result.verified) {
                            Log.i("Cris","Usuario verificado")
                            changedNavigateToHome(true)
                        } else {
                            Log.i("Cris","Usuario NO verificado")
                            changedNavigateToVerifyAccount()
                        }
                    }
                }
                _viewState.value = LoginViewState(isLoading = false)
            }
        }*/

    private fun isValidEmail(email: String): Boolean {
        val b = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        return b && email.isNotEmpty()
    }

    private fun isValidPassword(password: String): Boolean {
        val b = password.length >= MIN_PASSWORD_LENGTH
        return b && password.isNotEmpty()
    }
}