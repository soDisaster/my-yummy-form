package com.technicaltest.myyummyform.extension

import android.content.Context
import android.content.Intent
import android.util.Log

fun Context.sendEmail(json: String) {
    try {
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("test@test.com"))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Answers to form")
        intent.putExtra(Intent.EXTRA_TEXT, json)
        intent.type = "message/rfc822"
        startActivity(intent)
    } catch (e: Exception) {
        Log.e("Error", "Error")
    }
}