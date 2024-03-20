package com.freedomus.project.app.ui.login.register.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EditTextApp(
    texto: String,
    S: String,
    t:Boolean,
    onTextChange: (String) -> Unit,
) {
    if (!t){
        Text(text = "Introducir un correo valido", modifier= Modifier.padding(4.dp).fillMaxWidth(), fontWeight = FontWeight.SemiBold, color = Color.Red, fontSize = 12.sp)
    }

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
            unfocusedBorderColor = if (t) Color(0xFFF5F5F5) else Color.Red,
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
