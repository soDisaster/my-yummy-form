package com.technicaltest.myyummyform.repository

import android.content.Context
import com.google.gson.Gson
import com.technicaltest.myyummyform.data.YummyForm
import com.technicaltest.myyummyform.data.YummyFormItem
import com.technicaltest.myyummyform.utils.readJSONFromAssets

class DataRepository {

    fun getDataFromJson(context : Context): List<YummyFormItem> {
        val json = readJSONFromAssets(context)
        val list = Gson().fromJson(json, YummyForm::class.java).sortedBy { it.order }
        return list
    }
}