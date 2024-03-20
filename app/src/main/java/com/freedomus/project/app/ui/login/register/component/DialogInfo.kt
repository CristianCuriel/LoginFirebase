package com.freedomus.project.app.ui.login.register.component

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun DialogInfo(ShowDialogError: Boolean, textError: String, @DrawableRes imagen: Int, onDimiss: ()-> Unit) {

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