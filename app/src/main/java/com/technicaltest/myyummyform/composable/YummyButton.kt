package com.technicaltest.myyummyform.composable

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.technicaltest.myyummyform.R

@Composable
fun YummyButton(text: Int, action: () -> Unit) {
    Button(
        onClick = { action.invoke() },
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.purple_200))
    ) {
        Text(
            stringResource(id = text),
            style = TextStyle(fontSize = 16.sp, color = colorResource(id = R.color.white))
        )
    }
}