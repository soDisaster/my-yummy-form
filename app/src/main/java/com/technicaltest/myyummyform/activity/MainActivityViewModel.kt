package com.technicaltest.myyummyform.activity

import android.content.Context
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.technicaltest.myyummyform.data.AnswersToSend
import com.technicaltest.myyummyform.data.AnswersToSendItem
import com.technicaltest.myyummyform.data.Choice
import com.technicaltest.myyummyform.data.YummyFormItem
import com.technicaltest.myyummyform.repository.DataRepository
import com.technicaltest.myyummyform.utils.writeJsonFile
import org.koin.java.KoinJavaComponent.inject

class MainActivityViewModel : ViewModel() {

    private val dataRepository: DataRepository by inject(DataRepository::class.java)

    private var checkboxesResponses: LinkedHashMap<Int, List<Int>> = linkedMapOf()
    private var radioButtonsResponses: LinkedHashMap<Int, List<Int>> = linkedMapOf()
    private var answersToSend = AnswersToSend()

    fun getData(context: Context): List<YummyFormItem> = dataRepository.getDataFromJson(context)

    private fun writeJson(answersToSend: AnswersToSend) = writeJsonFile(answersToSend)

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
        val listAnswersCheckboxes =
            checkboxesResponses.map { AnswersToSendItem(it.key, it.value) }.toMutableList()
        val listAnswersRadioButton =
            radioButtonsResponses.map { AnswersToSendItem(it.key, it.value) }.toMutableList()
        answersToSend.addAll(listAnswersRadioButton)
        answersToSend.addAll(listAnswersCheckboxes)
        return writeJson(answersToSend)
    }

    fun isFormComplete(numberOfQuestions: Int) =
        answersToSend.size == numberOfQuestions
                && !answersToSend.any { it.choices.isEmpty() }

    fun clearResponses() {
        checkboxesResponses.clear()
        radioButtonsResponses.clear()
        answersToSend.clear()
    }

}