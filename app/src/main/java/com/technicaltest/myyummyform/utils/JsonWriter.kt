package com.technicaltest.myyummyform.utils

import android.os.Environment
import android.util.Log
import com.google.gson.Gson
import com.technicaltest.myyummyform.data.AnswersToSend
import java.io.File
import java.io.FileOutputStream


fun writeJsonFile(answersToSend: AnswersToSend): String {
    try {
        val file = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
            "formToSend.json"
        )
        file.createNewFile()
        val fileOutputStream = FileOutputStream(file)
        val json = Gson().toJson(answersToSend).toString()

        fileOutputStream.write(json.toByteArray())
        fileOutputStream.close()

        return json
    } catch (e: Exception) {
        Log.e("Exception", "File write failed: $e")
        return ""
    }
}