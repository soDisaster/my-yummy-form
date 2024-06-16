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

    private var checkboxesResponses: LinkedHashMap<Int, List<Int>> = linkedMapOf()
    private var radioButtonsResponses: LinkedHashMap<Int, List<Int>> = linkedMapOf()
    private var answersToSend = AnswersToSend()
    private var numberOfQuestions = 0

    fun readJson(context: Context): List<YummyFormItem> {
        val json = readJSONFromAssets(context)
        val list =  Gson().fromJson(json, YummyForm::class.java).sortedBy { it.order }
        numberOfQuestions = list.size
        return list
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

    fun saveRadioButtonsResponses(item: YummyFormItem, selectedRadioButtonAnswer: Choice?) {
        selectedRadioButtonAnswer?.let {
            radioButtonsResponses[item.id] = listOf(selectedRadioButtonAnswer.id)
        }
    }

    fun getAnswersToSend(): String {
        answersToSend = AnswersToSend()
        val listAnswersCheckboxes = checkboxesResponses.map { AnswersToSendItem(it.key, it.value) }.toMutableList()
        val listAnswersRadioButton = radioButtonsResponses.map { AnswersToSendItem(it.key, it.value) }.toMutableList()
        answersToSend.addAll(listAnswersCheckboxes)
        answersToSend.addAll(listAnswersRadioButton)
        return writeJson(answersToSend)
    }

    fun isFormComplete() =  answersToSend.size == numberOfQuestions

    fun clearResponses() {
        checkboxesResponses.clear()
        radioButtonsResponses.clear()
        answersToSend.clear()
    }

}