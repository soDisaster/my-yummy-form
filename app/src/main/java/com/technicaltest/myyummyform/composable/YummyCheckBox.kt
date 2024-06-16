package com.technicaltest.myyummyform.composable

import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.technicaltest.myyummyform.R
import com.technicaltest.myyummyform.data.Choice

@Composable
fun YummyCheckBox(
    selectedCheckboxesAnswers: SnapshotStateList<List<Choice>>,
    response: Choice,
    onCheckedChange: () -> Unit
) {
    Checkbox(
        colors = CheckboxDefaults.colors(checkedColor = colorResource(id = R.color.purple_500)),
        checked = selectedCheckboxesAnswers.any { it.contains(response) },
        onCheckedChange = {
            onCheckedChange.invoke()
        }
    )
    Text(
        text = response.name,
        style = TextStyle(
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            color = if (selectedCheckboxesAnswers.any { it.contains(response) }) {
                colorResource(id = R.color.purple_500)
            } else {
                colorResource(id = R.color.black)
            }
        )
    )
}