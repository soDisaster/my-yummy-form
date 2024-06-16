package com.technicaltest.myyummyform.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.technicaltest.myyummyform.R
import com.technicaltest.myyummyform.composable.YummyButton
import com.technicaltest.myyummyform.navigation.Form

@Composable
fun HomeScreen(navController: NavHostController) {
    Box(contentAlignment = Alignment.BottomCenter) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .size(250.dp)
                    .padding(bottom = 16.dp),
                painter = painterResource(id = R.drawable.cheese),
                contentDescription = "Beautiful raclette"
            )
            Text(
                modifier = Modifier.padding(top = 16.dp, bottom = 24.dp),
                text = stringResource(id = R.string.home_subtitle),
                style = TextStyle(
                    fontStyle = FontStyle.Italic, fontSize = 24.sp, color = colorResource(
                        id = R.color.purple_500
                    )
                )
            )
        }
        YummyButton(R.string.home_button) {
            navController.navigate(Form)
        }
    }
}
