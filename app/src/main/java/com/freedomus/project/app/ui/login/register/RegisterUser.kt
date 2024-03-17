package com.freedomus.project.app.ui.login.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.freedomus.project.app.core.routes.Routes
import com.freedomus.project.app.ui.login.login.component.TopAppBarLogin

@Composable
fun RegisterUser(navigationController: NavHostController) {

    val registerViewModel = viewModel<RegisterViewModel>()
    val nombre: String by registerViewModel.nombre.observeAsState(initial = "")
    val apellido: String by registerViewModel.apellido.observeAsState(initial = "")
    val contra: String by registerViewModel.contra.observeAsState(initial = "")
    val contraConfir: String by registerViewModel.contraConfir.observeAsState(initial = "")

    Scaffold(topBar = {
        TopAppBarLogin(
            "Iniciar Sesion",
            Color(0xFFF5B201),
            { navigationController.navigate(Routes.Login.route) },
            { navigationController.popBackStack(Routes.Introduction.route, false) })
    }, containerColor = Color.White) {
        Column(
            Modifier
                .padding(it)
                .fillMaxWidth()
        ) {

            FormRegister(nombre, apellido, contra, contraConfir, registerViewModel)

        }
    }
}

@Composable
fun FormRegister(
    nombre: String,
    apellido: String,
    contra: String,
    contraConfir: String,
    registerViewModel: RegisterViewModel,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        EditTextApp(texto = nombre, "Nombre") {
            registerViewModel.onRegisterChanged(
                nombre = it,
                apellido = apellido,
                contra = contra,
                contraConfir = contraConfir
            )
        }

        EditTextApp(texto = apellido, "Apellido") {
            registerViewModel.onRegisterChanged(
                nombre = nombre,
                apellido = it,
                contra = contra,
                contraConfir = contraConfir
            )
        }

        Password(password = contra, true, "Contraseña") {
            registerViewModel.onRegisterChanged(
                nombre = nombre,
                apellido = apellido,
                contra = it,
                contraConfir = contraConfir
            )
        }

        Password(password = contraConfir, true, "Confirmar contraseña") {
            registerViewModel.onRegisterChanged(
                nombre = nombre,
                apellido = apellido,
                contra = contra,
                contraConfir = it
            )
        }
    }

}

@Composable
fun EditTextApp(
    texto: String,
    S: String,
    onTextChange: (String) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 14.dp)
    ) {

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            value = texto,
            onValueChange = { onTextChange(it) },
            maxLines = 1,
            singleLine = true,
            placeholder = { Text(S)},
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFF5F5F5),
                unfocusedBorderColor = Color(0xFFF5F5F5),
                unfocusedPlaceholderColor = Color.DarkGray,
                focusedBorderColor = Color(0xFFF5B201),
                focusedContainerColor = Color.Transparent
            ),
            shape = RoundedCornerShape(26.dp),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            )

        )

    }//Column

}

@Composable
fun Password(
    password: String,
    visible: Boolean,
    S: String,
    onTextChange: (String) -> Unit,
) {

    var passwordVisibility by remember { mutableStateOf(false) }

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
            placeholder = { Text(S) },
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
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
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