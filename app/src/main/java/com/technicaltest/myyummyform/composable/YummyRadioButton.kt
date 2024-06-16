package com.technicaltest.myyummyform.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.technicaltest.myyummyform.R
import com.technicaltest.myyummyform.data.Choice

@Composable
fun YummyRadioButton(
    selectedRadioButtonAnswer: Choice?,
    response: Choice,
    onClick: () -> Unit
) {

    val showDialog = remember { mutableStateOf(false) }

    RadioButton(
        selected = selectedRadioButtonAnswer == response,
        onClick = {
            onClick.invoke()
        },
        colors = RadioButtonDefaults.colors(selectedColor = colorResource(id = R.color.purple_500))
    )
    Text(
        text = response.name, style = TextStyle(
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            color = if (selectedRadioButtonAnswer == response) {
                colorResource(id = R.color.purple_500)
            } else {
                colorResource(id = R.color.black)
            }
        )
    )
    if (response.description != null) {
        Icon(
            modifier = Modifier
                .padding(start = 8.dp)
                .clickable {
                    showDialog.value = true
                },
            imageVector = Icons.Default.Info,
            contentDescription = "",
            tint = colorResource(id = R.color.purple_500)
        )
        AnimatedVisibility(visible = showDialog.value) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                confirmButton = {
                    Button(onClick = { showDialog.value = false }) {
                        Text(text = "Ok")
                    }
                },
                text = { Text(text = response.description) })
        }
    }
}