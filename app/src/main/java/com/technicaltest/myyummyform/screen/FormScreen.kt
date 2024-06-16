package com.technicaltest.myyummyform.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.technicaltest.myyummyform.R
import com.technicaltest.myyummyform.activity.MainActivityViewModel
import com.technicaltest.myyummyform.composable.Question
import com.technicaltest.myyummyform.composable.YummyButton
import com.technicaltest.myyummyform.composable.YummyCheckBox
import com.technicaltest.myyummyform.composable.YummyRadioButton
import com.technicaltest.myyummyform.data.Choice
import com.technicaltest.myyummyform.navigation.Success
import kotlinx.coroutines.launch

@Composable
fun FormScreen(
    navController: NavHostController,
    viewModel: MainActivityViewModel,
    snackBarHostState: SnackbarHostState
) {

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, top = 24.dp)
            .verticalScroll(rememberScrollState()),
    ) {

        val data = viewModel.readJson(LocalContext.current)

        data.forEachIndexed { index, item ->

            val choices = item.choices.sortedBy { it.order }
            var selectedRadioButtonAnswer by remember { mutableStateOf<Choice?>(null) }
            val selectedCheckboxesAnswers =
                remember { mutableStateListOf<List<Choice>>(emptyList()) }

            Question(index, item)

            Column(
                modifier = Modifier.padding(bottom = 8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                choices.sortedBy { it.order }.forEach { response ->
                    if (item.multiple) {
                        Row(
                            modifier = Modifier.padding(top = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
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

        YummyButton(text = R.string.form_button) {
            val answersToSend = viewModel.getAnswersToSend()
            if (viewModel.isFormComplete()) {
                viewModel.clearResponses()
                navController.navigate(Success(answersToSend))
            } else {
                coroutineScope.launch {
                    snackBarHostState.showSnackbar(
                        "Please fill all answers",
                        duration = SnackbarDuration.Short,
                        withDismissAction = true
                    )
                }
            }
        }
    }
}
