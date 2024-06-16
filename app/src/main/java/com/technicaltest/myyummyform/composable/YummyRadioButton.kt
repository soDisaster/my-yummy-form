package com.technicaltest.myyummyform.composable

import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.technicaltest.myyummyform.data.Choice

@Composable
fun YummyRadioButton(
    selectedRadioButtonAnswer: Choice?,
    response: Choice,
    onClick: () -> Unit
) {
    RadioButton(
        selected = selectedRadioButtonAnswer == response,
        onClick = {
            onClick.invoke()
        })
    Text(response.name)
}