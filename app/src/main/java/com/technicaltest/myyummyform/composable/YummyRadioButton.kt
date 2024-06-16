package com.technicaltest.myyummyform.composable

import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.technicaltest.myyummyform.R
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
        },
        colors = RadioButtonDefaults.colors(selectedColor = colorResource(id = R.color.purple_200))
    )
    Text(
        text = response.name, style = TextStyle(
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            color = if (selectedRadioButtonAnswer == response) {
                colorResource(id = R.color.purple_200)
            } else {
                colorResource(id = R.color.black)
            }
        )
    )
}