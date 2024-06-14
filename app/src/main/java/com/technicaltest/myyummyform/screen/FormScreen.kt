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
import com.technicaltest.myyummyform.data.Choice
import com.technicaltest.myyummyform.data.YummyForm
import com.technicaltest.myyummyform.utils.readJSONFromAssets

@Composable
fun FormScreen(navController: NavHostController) {

    val json = readJSONFromAssets(LocalContext.current)
    val data = Gson().fromJson(json, YummyForm::class.java).sortedBy { it.order }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        data.forEach { item ->
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
                val selectedChoices = remember { mutableStateListOf(listOf<Choice>()) }

                Column {
                    item.choices.sortedBy { it.order }.forEach { response ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = selectedChoices.any { it.contains(response) },
                                onCheckedChange = {
                                    if (selectedChoices.any { it.contains(response) }) {
                                        selectedChoices.remove(listOf(response))
                                    } else {
                                        selectedChoices.add(listOf(response))
                                    }
                                }
                            )
                            Text(response.name)
                        }
                    }
                }
            } else {
                // Radio Buttons
                var selectedOption by remember { mutableStateOf(item.choices.first()) }

                Column {
                    item.choices.sortedBy { it.order }.forEach { response ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = selectedOption == response,
                                onClick = { selectedOption = response })
                            Text(response.name)
                        }
                    }
                }
            }
        }

        // Send form
        YummyButton(text = R.string.form_button) { navController.popBackStack() }
    }
}
