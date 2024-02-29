package com.freedomus.project.app.core.routes

sealed class Routes(val route:String){
    object Introduction:Routes("Introduction")
    object Login:Routes("Login")
    object VerificationUser:Routes("VerificationUser")

    object Home:Routes("Home")


}