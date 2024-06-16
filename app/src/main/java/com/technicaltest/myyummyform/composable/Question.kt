package com.technicaltest.myyummyform.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.technicaltest.myyummyform.R
import com.technicaltest.myyummyform.data.YummyFormItem

@Composable
fun Question(index: Int, item: YummyFormItem) {

    val questionNumber = index + 1

    Text(
        modifier = Modifier.padding(bottom = 8.dp, end = 16.dp),
        text = "$questionNumber. ${item.question}",
        style = TextStyle(
            color = colorResource(id = R.color.purple_500),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            textAlign = TextAlign.Start
        )
    )
}