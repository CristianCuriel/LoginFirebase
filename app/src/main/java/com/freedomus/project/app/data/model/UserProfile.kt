package com.freedomus.project.app.data.model

import android.net.Uri

data class UserProfile(
    val name: String?,
    val email: String?,
    val photoUrl: Uri?,
    val emailVerified: Boolean,
    val uid: String
)