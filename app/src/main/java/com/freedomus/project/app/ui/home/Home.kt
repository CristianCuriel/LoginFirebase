package com.freedomus.project.app.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
<<<<<<< HEAD
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
=======
>>>>>>> origin/master
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.freedomus.project.R
<<<<<<< HEAD
import com.freedomus.project.app.core.routes.Routes
=======
>>>>>>> origin/master

@Composable
fun Home(homeViewModel: HomeViewModel, navigationController: NavHostController) {

<<<<<<< HEAD
    //val backToNavigateLogin by homeViewModel.backToNavigateLogin.collectAsState()

=======
>>>>>>> origin/master
    Column(
        Modifier
            .fillMaxWidth()
            .padding(14.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Bienvenido",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            letterSpacing = 2.sp
        )
        Spacer(modifier = Modifier.size(28.dp))
        ImgVerification()
        Spacer(modifier = Modifier.size(28.dp))
<<<<<<< HEAD
        homeViewModel.userinfoProfile()?.email?.let {
            Text(
                text = it,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                letterSpacing = 2.sp
            )
        }

        Spacer(modifier = Modifier.size(28.dp))

        Button(onClick = {
            navigationController.popBackStack(Routes.Login.route, false)
            homeViewModel.signOutUser()
        }
        ) {
=======
        Text(
            text = homeViewModel.userinfoProfile()?.email!!,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            letterSpacing = 2.sp
        )

        Spacer(modifier = Modifier.size(28.dp))

        Button(onClick = { }) {
>>>>>>> origin/master
            Text(text = "Cerrar sesion")
        }
    }

<<<<<<< HEAD
    //back(backToNavigateLogin, navigationController, homeViewModel)

=======
>>>>>>> origin/master
}

@Composable
private fun ImgVerification() {
    Image(
        painter = painterResource(R.drawable.mobileapp),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(300.dp, 300.dp)
    )
<<<<<<< HEAD
}

private fun back(
    backToNavigateLogin: Boolean,
    navigationController: NavHostController,
    homeViewModel: HomeViewModel
) {
   if(backToNavigateLogin){
       homeViewModel.changed()
        navigationController.popBackStack(Routes.Login.route, false)
    }
=======
>>>>>>> origin/master
}