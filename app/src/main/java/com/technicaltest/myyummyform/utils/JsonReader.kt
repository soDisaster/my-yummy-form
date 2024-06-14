package com.technicaltest.myyummyform.utils

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

// https://gist.github.com/delasign/9557424fd94f45e00212d46e27dc891d#file-readjsonfromassets-kt

fun readJSONFromAssets(context: Context): String {
    try {
        val file = context.assets.open("data.json")
        val bufferedReader = BufferedReader(InputStreamReader(file))
        val stringBuilder = StringBuilder()
        bufferedReader.useLines { lines ->
            lines.forEach {
                stringBuilder.append(it)
            }
        }
        val jsonString = stringBuilder.toString()
        return jsonString
    } catch (e: Exception) {
        e.printStackTrace()
        return ""
    }
}