package com.freedomus.project.app.ui.login.register.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.freedomus.project.app.ui.login.register.RegisterViewModel
import com.freedomus.project.app.ui.login.register.StateDataRegister

@Composable
fun FormRegister(
    nombre: String,
    apellido: String,
    contra: String,
    contraConfir: String,
    correo: String,
    registerViewModel: RegisterViewModel,
    viewStateVerifi: StateDataRegister
) {

    Column(
        Modifier
            .fillMaxWidth()
            .padding(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        EditTextApp(texto = nombre, "Nombre", true) {
            registerViewModel.onRegisterChanged(
                nombre = it,
                apellido = apellido,
                contra = contra,
                correo = correo,
                contraConfir = contraConfir
            )
        }

        EditTextApp(texto = apellido, "Apellido", true) {
            registerViewModel.onRegisterChanged(
                nombre = nombre,
                apellido = it,
                contra = contra,
                correo = correo,
                contraConfir = contraConfir
            )
        }

        EditTextApp(texto = correo, "Correo", viewStateVerifi.isValidEmail) {
            registerViewModel.onRegisterChanged(
                nombre = nombre,
                apellido = apellido,
                contra = contra,
                correo = it,
                contraConfir = contraConfir
            )
        }

        Password(password = contra, S = "Contraseña", visible = viewStateVerifi.isValidContra) {
            registerViewModel.onRegisterChanged(
                nombre = nombre,
                apellido = apellido,
                contra = it,
                correo = correo,
                contraConfir = contraConfir
            )
        }

        Password(
            password = contraConfir,
            S = "Confirmar contraseña",
            visible = viewStateVerifi.isValidContraConfir
        ) {
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