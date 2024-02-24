package com.freedomus.project.app.ui.singIn.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.freedomus.project.R

@Preview
@Composable
fun OtherAccounts() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF1F1F1))
            .padding(top = 24.dp)
    ) {

        Column(Modifier.fillMaxWidth()) {

            Spacer(modifier = Modifier.padding(vertical = 14.dp))

            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp, horizontal = 14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp),
                onClick = { /*TODO*/ }) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        modifier = Modifier
                            .width(35.dp)
                            .height(35.dp),
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = null
                    )

                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = "Continuar con Google",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 16.sp
                    )

                    Icon(
                        imageVector = Icons.Filled.ArrowForward,
                        tint = Color.Black,
                        contentDescription = "Localized description"
                    )
                }
            }// Button

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp, horizontal = 14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp),
                onClick = { /*TODO*/ }) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        modifier = Modifier
                            .width(35.dp)
                            .height(35.dp),
                        painter = painterResource(id = R.drawable.facebook),
                        contentDescription = null
                    )

                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = "Continuar con Facebook",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 16.sp
                    )

                    Icon(
                        imageVector = Icons.Filled.ArrowForward,
                        tint = Color.Black,
                        contentDescription = "Localized description"
                    )
                }
            }// Buttom

        }// Column
        //Spacer(modifier = Modifier.padding(vertical = 10.dp))
    }
}
