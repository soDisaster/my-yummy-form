package com.technicaltest.myyummyform.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.technicaltest.myyummyform.data.YummyFormItem

@Composable
fun Question(item: YummyFormItem) {
    Text(
        modifier = Modifier.padding(bottom = 2.dp),
        text = item.question,
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
    )
}