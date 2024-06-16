package com.technicaltest.myyummyform.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.technicaltest.myyummyform.R
import com.technicaltest.myyummyform.activity.MainActivityViewModel
import com.technicaltest.myyummyform.composable.Question
import com.technicaltest.myyummyform.composable.YummyButton
import com.technicaltest.myyummyform.composable.YummyCheckBox
import com.technicaltest.myyummyform.composable.YummyRadioButton
import com.technicaltest.myyummyform.data.AnswersToSend
import com.technicaltest.myyummyform.data.Choice
import com.technicaltest.myyummyform.navigation.Success

@Composable
fun FormScreen(navController: NavHostController, viewModel: MainActivityViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        val data = viewModel.readJson(LocalContext.current)

        data.forEach { item ->

            val choices = item.choices.sortedBy { it.order }
            var selectedRadioButtonAnswer by remember { mutableStateOf(choices.first()) }
            val selectedCheckboxesAnswers =
                remember { mutableStateListOf<List<Choice>>(emptyList()) }

            Question(item)

            Column {
                choices.sortedBy { it.order }.forEach { response ->
                    if (item.multiple) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            YummyCheckBox(
                                selectedCheckboxesAnswers,
                                response
                            ) {
                                if (selectedCheckboxesAnswers.any { it.contains(response) }) {
                                    selectedCheckboxesAnswers.remove(listOf(response))
                                } else {
                                    selectedCheckboxesAnswers.add(listOf(response))
                                }

                                viewModel.saveCheckboxesResponses(item, selectedCheckboxesAnswers)
                            }
                        }
                    } else {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            YummyRadioButton(
                                selectedRadioButtonAnswer,
                                response
                            ) {
                                selectedRadioButtonAnswer = response
                                viewModel.saveRadioButtonsResponses(item, selectedRadioButtonAnswer)
                            }
                        }
                    }
                }
            }
        }

        // Send form
        YummyButton(text = R.string.form_button) {
            navController.navigate(Success(viewModel.getAnswersToSend()))
        }
    }
}
