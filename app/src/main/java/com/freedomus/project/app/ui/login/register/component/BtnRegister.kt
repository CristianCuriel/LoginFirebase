package com.freedomus.project.app.ui.login.register.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.freedomus.project.app.ui.login.register.StateDataRegister

@Composable
fun BtnRegister(viewStateVerifi: StateDataRegister, register: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 14.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
        onClick = { register() }
    ) {

        if(viewStateVerifi.isLoading) {
            CircularProgressIndicator(strokeWidth = 2.dp, color = Color.White, modifier = Modifier.size(6.dp))
        }

        Text(
            text = "Regitrarme!",
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier.padding(4.dp)
        )
    }
}
