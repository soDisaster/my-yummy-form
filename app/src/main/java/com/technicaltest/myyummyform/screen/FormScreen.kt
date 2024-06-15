package com.technicaltest.myyummyform.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.technicaltest.myyummyform.R
import com.technicaltest.myyummyform.composable.YummyButton
import com.technicaltest.myyummyform.data.AnswersToSend
import com.technicaltest.myyummyform.data.AnswersToSendItem
import com.technicaltest.myyummyform.data.Choice
import com.technicaltest.myyummyform.data.YummyForm
import com.technicaltest.myyummyform.navigation.Success
import com.technicaltest.myyummyform.utils.readJSONFromAssets
import com.technicaltest.myyummyform.utils.writeJsonFile

@Composable
fun FormScreen(navController: NavHostController) {

    val json = readJSONFromAssets(LocalContext.current)
    val data = Gson().fromJson(json, YummyForm::class.java).sortedBy { it.order }
    val context = LocalContext.current

    val answersToSend = remember { mutableStateOf(AnswersToSend()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        data.forEach { item ->

            var selectedRadioButtonAnswer by remember { mutableStateOf(item.choices.first()) }
            val selectedCheckboxesAnswers =
                remember { mutableStateListOf<List<Choice>>(emptyList()) }

            // Question
            Text(
                modifier = Modifier.padding(bottom = 2.dp),
                text = item.question,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
            )

            // Choices
            if (item.multiple) {
                // Checkboxes
                Column {
                    item.choices.sortedBy { it.order }.forEach { response ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = selectedCheckboxesAnswers.any { it.contains(response) },
                                onCheckedChange = {
                                    if (selectedCheckboxesAnswers.any { it.contains(response) }) {
                                        selectedCheckboxesAnswers.remove(listOf(response))
                                    } else {
                                        selectedCheckboxesAnswers.add(listOf(response))
                                    }
                                    answersToSend.value.add(
                                        AnswersToSendItem(
                                            id = item.id,
                                            choices = selectedCheckboxesAnswers.flatten()
                                                .map { it.id }
                                        )
                                    )
                                }
                            )
                            Text(response.name)
                        }
                    }
                }
            } else {
                // Radio Buttons
                Column {
                    item.choices.sortedBy { it.order }.forEach { response ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = selectedRadioButtonAnswer == response,
                                onClick = {
                                    selectedRadioButtonAnswer = response
                                    answersToSend.value.add(
                                        AnswersToSendItem(
                                            id = item.id,
                                            choices = listOf(
                                                selectedRadioButtonAnswer.id
                                            )
                                        )
                                    )
                                })
                            Text(response.name)
                        }
                    }
                }
            }
        }

        // Send form
        YummyButton(text = R.string.form_button) {
            val textToDisplay = writeJsonFile(answersToSend.value)
            navController.navigate(Success(textToDisplay))
        }
    }
}
