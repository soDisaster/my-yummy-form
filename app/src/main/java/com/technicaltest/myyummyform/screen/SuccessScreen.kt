package com.technicaltest.myyummyform.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.technicaltest.myyummyform.R
import com.technicaltest.myyummyform.composable.YummyButton
import com.technicaltest.myyummyform.navigation.Home

@Composable
fun SuccessScreen(navController: NavHostController, textFile: String) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {

        Text(
            text = stringResource(id = R.string.success_congrats_title),
            style = TextStyle(
                color = colorResource(id = R.color.purple_700),
                fontSize = 32.sp,
                textAlign = TextAlign.Center
            )
        )

        Text(
            modifier = Modifier.padding(horizontal = 24.dp),
            text = textFile,
            style = TextStyle(textAlign = TextAlign.Start)
        )

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(id = R.string.success_info_text),
            style = TextStyle(
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.purple_500),
                fontSize = 16.sp,
            )
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            YummyButton(R.string.success_send_by_email_button) {

            }

            YummyButton(R.string.success_finish_button) {
                navController.navigate(Home)
            }
        }
    }
}
