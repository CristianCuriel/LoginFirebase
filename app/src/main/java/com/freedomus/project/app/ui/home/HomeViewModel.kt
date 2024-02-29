package com.freedomus.project.app.ui.home

import androidx.lifecycle.ViewModel
import com.freedomus.project.app.domain.UserInfoProfile

class HomeViewModel : ViewModel() {

    private val userInfoProfile = UserInfoProfile()

    fun userinfoProfile() = userInfoProfile()


}