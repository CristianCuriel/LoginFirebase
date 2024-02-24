package com.freedomus.project.app.ui.singIn.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun EditTextLogin() {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 14.dp)
            .height(60.dp),
        value = "",
        onValueChange = { },
        placeholder = { Text("Nombre de usuario") },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFFF5F5F5),
            unfocusedBorderColor = Color(0xFFF5F5F5),
            unfocusedPlaceholderColor = Color.DarkGray
        ),
        shape = RoundedCornerShape(26.dp)
    )

    Spacer(modifier = Modifier.padding(vertical = 4.dp))

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 14.dp)
            .height(60.dp),
        value = "",
        onValueChange = { },
        placeholder = { Text("Contraseña") },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFFF5F5F5),
            unfocusedBorderColor = Color(0xFFF5F5F5),
            unfocusedPlaceholderColor = Color.DarkGray
        ),
        shape = RoundedCornerShape(26.dp)
    )
}