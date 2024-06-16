package com.technicaltest.myyummyform.composable

import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.res.colorResource
import com.technicaltest.myyummyform.R
import com.technicaltest.myyummyform.data.Choice

@Composable
fun YummyCheckBox(
    selectedCheckboxesAnswers: SnapshotStateList<List<Choice>>,
    response: Choice,
    onCheckedChange: () -> Unit
) {
    Checkbox(
        colors = CheckboxDefaults.colors(checkedColor = colorResource(id = R.color.purple_200)),
        checked = selectedCheckboxesAnswers.any { it.contains(response) },
        onCheckedChange = {
            onCheckedChange.invoke()
        }
    )
    Text(response.name)
}