package com.freedomus.project.app.ui.login.register

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.freedomus.project.R
import com.freedomus.project.app.core.routes.Routes
import com.freedomus.project.app.ui.login.login.component.TopAppBarLogin

@Composable
fun RegisterUser(navigationController: NavHostController) {

    val registerViewModel = viewModel<RegisterViewModel>()
    val nombre: String by registerViewModel.nombre.observeAsState(initial = "")
    val apellido: String by registerViewModel.apellido.observeAsState(initial = "")
    val contra: String by registerViewModel.contra.observeAsState(initial = "")
    val correo: String by registerViewModel.correo.observeAsState(initial = "")
    val contraConfir: String by registerViewModel.contraConfir.observeAsState(initial = "")
    val textErrorRegister:String by registerViewModel.textErrorRegister.observeAsState(initial = "")


    val navigateVerificationEmail by registerViewModel.navigateVerificationEmail.collectAsState()
    val ShowDialogError by registerViewModel.ShowDialogError.collectAsState()

    Scaffold(topBar = {
        TopAppBarLogin(
            "Iniciar Sesion",
            Color(0xFFF5B201),
            { navigationController.navigate(Routes.Login.route) },
            { navigationController.popBackStack(Routes.Introduction.route, false) })
    }, containerColor = Color.White) {

        LaunchedEffect(navigateVerificationEmail) {
            if (navigateVerificationEmail) {
                navigationController.navigate(Routes.VerificationUser.route)
                registerViewModel.changedStateNavigateVerification()
            }
        }

        Column(
            Modifier
                .padding(it)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Registro de usuarios",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            FormRegister(nombre, apellido, contra, contraConfir, correo, registerViewModel)
            BtnRegister() { registerViewModel.registerUser() }
            DialogInfo(ShowDialogError, textErrorRegister, R.drawable.deniedregister){registerViewModel.onDimissDialogError()}

        }
    }
}

@Composable
fun DialogInfo(ShowDialogError: Boolean, textError: String, @DrawableRes imagen: Int  ,onDimiss: ()-> Unit) {

    if (ShowDialogError)

        Dialog(
            onDismissRequest = { },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, Color(0xFFF5B201)),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    Image(
                        painter = painterResource(imagen),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .height(160.dp)

                    )

                    Text(
                        text = "Error",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray,
                        modifier = Modifier.padding(16.dp),
                    )

                    Text(
                        text = textError,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.DarkGray,
                        modifier = Modifier.padding(16.dp),
                    )

                    TextButton(
                        onClick = { onDimiss() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Dismiss")
                    }

                }
            }
        }

}

@Composable
fun BtnRegister(register: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 14.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
        onClick = { register() }
    ) {
        Text(
            text = "Regitrarme!",
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
fun FormRegister(
    nombre: String,
    apellido: String,
    contra: String,
    contraConfir: String,
    correo: String,
    registerViewModel: RegisterViewModel,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        EditTextApp(texto = nombre, "Nombre") {
            registerViewModel.onRegisterChanged(
                nombre = it,
                apellido = apellido,
                contra = contra,
                correo = correo,
                contraConfir = contraConfir
            )
        }

        EditTextApp(texto = apellido, "Apellido") {
            registerViewModel.onRegisterChanged(
                nombre = nombre,
                apellido = it,
                contra = contra,
                correo = correo,
                contraConfir = contraConfir
            )
        }

        EditTextApp(texto = correo, "Correo") {
            registerViewModel.onRegisterChanged(
                nombre = nombre,
                apellido = apellido,
                contra = contra,
                correo = it,
                contraConfir = contraConfir
            )
        }

        Password(password = contra, true, "Contraseña") {
            registerViewModel.onRegisterChanged(
                nombre = nombre,
                apellido = apellido,
                contra = it,
                correo = correo,
                contraConfir = contraConfir
            )
        }

        Password(password = contraConfir, true, "Confirmar contraseña") {
            registerViewModel.onRegisterChanged(
                nombre = nombre,
                apellido = apellido,
                contra = contra,
                correo = correo,
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
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 14.dp)
            .height(60.dp),
        value = texto,
        onValueChange = { onTextChange(it) },
        maxLines = 1,
        singleLine = true,
        placeholder = { Text(S) },
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