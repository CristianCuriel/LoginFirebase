package com.freedomus.project.app.ui.login.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.freedomus.project.app.ui.login.LoginViewModel
import com.freedomus.project.app.ui.login.LoginViewState


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EditTextLogin(loginViewModel: LoginViewModel, uiState: LoginViewState) {
    var keyboardController = LocalSoftwareKeyboardController.current

    val email: String by loginViewModel.email.observeAsState(initial = "")
    val password: String by loginViewModel.password.observeAsState(initial = "")

    Email(email, uiState.isValidEmail, keyboardController!!) { c ->
        loginViewModel.onLoginChanged(email = c, password = password)
    }

    Spacer(modifier = Modifier.padding(vertical = 4.dp))

    Password(password, uiState.isValidPassword, keyboardController!!) {
        loginViewModel.onLoginChanged(email = email, password = it)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Password(password: String, visible: Boolean, keyboardController: SoftwareKeyboardController, onTextChange: (String) -> Unit) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp, horizontal = 14.dp)) {

        if (!visible) {
            Text(
                "Contraseña no valida, verificar",
                fontSize = 12.sp,
                color = Color.Red,
                fontWeight = FontWeight.SemiBold,
            )
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            value = password,
            onValueChange = { onTextChange(it) },
            maxLines = 1,
            placeholder = { Text("Contraseña") },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFF5F5F5),
                unfocusedBorderColor = Color(0xFFF5F5F5),
                unfocusedPlaceholderColor = Color.DarkGray
            ),
            shape = RoundedCornerShape(26.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        )
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Email(email: String, visible: Boolean, keyboardController: SoftwareKeyboardController, onTextChange: (String) -> Unit) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp, horizontal = 14.dp)) {

        if(!visible) {
            Text(
                "Correo no valido, verificar",
                fontSize = 12.sp,
                color = Color.Red,
                fontWeight = FontWeight.SemiBold,
            )
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            value = email,
            onValueChange = { onTextChange(it) },
            maxLines = 1,
            singleLine = true,
            placeholder = { Text("Nombre de usuario") },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFF5F5F5),
                unfocusedBorderColor = Color(0xFFF5F5F5),
                unfocusedPlaceholderColor = Color.DarkGray
            ),
            shape = RoundedCornerShape(26.dp)
        )

    }//Column

}