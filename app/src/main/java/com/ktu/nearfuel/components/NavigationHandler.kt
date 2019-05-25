package com.ktu.nearfuel.components

import android.content.Context
import android.content.Intent
import android.net.Uri


object NavigationHandler{

    fun openNavigation(context: Context, lng: String, lat: String){
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("http://maps.google.com/maps?daddr=$lat,$lng")
        )
        context.startActivity(intent)
    }
}