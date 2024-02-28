package com.freedomus.project.app.data.network

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseClient {

    val auth = FirebaseAuth.getInstance()
    val db = Firebase.firestore

}