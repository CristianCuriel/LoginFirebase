package com.freedomus.project.app.ui.login.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.freedomus.project.app.ui.login.LoginViewModel
import com.freedomus.project.app.ui.login.LoginViewState


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EditTextLogin(loginViewModel: LoginViewModel, uiState: LoginViewState) {

    val email: String by loginViewModel.email.observeAsState(initial = "")
    val password: String by loginViewModel.password.observeAsState(initial = "")

    Email(email, uiState.isValidEmail) { c ->
        loginViewModel.onLoginChanged(email = c, password = password)
    }

    Spacer(modifier = Modifier.padding(vertical = 4.dp))

    Password(password, uiState.isValidPassword) {
        loginViewModel.onLoginChanged(email = email, password = it)
    }
}


@Composable
fun Password(
    password: String,
    visible: Boolean,
    onTextChange: (String) -> Unit,
) {

    var passwordVisibility by remember { mutableStateOf(false)}

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 14.dp)
    ) {

        if (!visible) {
            Text(
                "La contraseña debe tener minimo 6 caracteres",
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
                unfocusedPlaceholderColor = Color.DarkGray,
                focusedBorderColor = Color(0xFFF5B201),
                focusedContainerColor = Color.Transparent
            ),
            shape = RoundedCornerShape(26.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            trailingIcon = {
                val imagen = if (passwordVisibility) {
                    Icons.Filled.VisibilityOff
                } else {
                    Icons.Filled.Visibility
                }
                IconButton(onClick = {passwordVisibility = !passwordVisibility}){
                    Icon(imageVector = imagen, contentDescription = "show password")
                }

            },

            visualTransformation = if (passwordVisibility) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        )
    }
}


@Composable
fun Email(
    email: String,
    visible: Boolean,
    onTextChange: (String) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 14.dp)
    ) {

        if (!visible) {
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
                unfocusedPlaceholderColor = Color.DarkGray,
                focusedBorderColor = Color(0xFFF5B201),
                focusedContainerColor = Color.Transparent
            ),
            shape = RoundedCornerShape(26.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )

        )

    }//Column

}