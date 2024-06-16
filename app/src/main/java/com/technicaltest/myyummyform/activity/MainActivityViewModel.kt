package com.technicaltest.myyummyform.activity

import android.content.Context
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.technicaltest.myyummyform.data.AnswersToSend
import com.technicaltest.myyummyform.data.AnswersToSendItem
import com.technicaltest.myyummyform.data.Choice
import com.technicaltest.myyummyform.data.YummyForm
import com.technicaltest.myyummyform.data.YummyFormItem
import com.technicaltest.myyummyform.utils.readJSONFromAssets
import com.technicaltest.myyummyform.utils.writeJsonFile

class MainActivityViewModel : ViewModel() {

    private var checkboxesResponses: MutableMap<Int, List<Int>> = mutableMapOf()
    private var radioButtonsResponses: MutableMap<Int, List<Int>> = mutableMapOf()

    fun readJson(context: Context): List<YummyFormItem> {
        val json = readJSONFromAssets(context)
        return Gson().fromJson(json, YummyForm::class.java).sortedBy { it.order }
    }

    private fun writeJson(answersToSend: AnswersToSend): String {
        return writeJsonFile(answersToSend)
    }

    fun saveCheckboxesResponses(
        item: YummyFormItem,
        selectedCheckboxesAnswers: SnapshotStateList<List<Choice>>
    ) {
        checkboxesResponses[item.id] = selectedCheckboxesAnswers.flatten().map { it.id }
    }

    fun saveRadioButtonsResponses(item: YummyFormItem, selectedRadioButtonAnswer: Choice) {
        radioButtonsResponses[item.id] = listOf(selectedRadioButtonAnswer.id)
    }

    fun getAnswersToSend(): String {
        val answersToSend = AnswersToSend()
        val listAnswersCheckboxes = checkboxesResponses.map { AnswersToSendItem(it.key, it.value) }.toMutableList()
        val listAnswersRadioButton = radioButtonsResponses.map { AnswersToSendItem(it.key, it.value) }.toMutableList()
        answersToSend.addAll(listAnswersCheckboxes)
        answersToSend.addAll(listAnswersRadioButton)
        return writeJson(answersToSend)
    }
}