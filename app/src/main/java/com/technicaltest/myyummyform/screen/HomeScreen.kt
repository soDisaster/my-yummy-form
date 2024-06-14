package com.technicaltest.myyummyform.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.technicaltest.myyummyform.R
import com.technicaltest.myyummyform.navigation.NavigationItem

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.home_title),
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp)
        )
        Text(
            modifier = Modifier.padding(vertical = 16.dp),
            text = stringResource(id = R.string.home_subtitle),
            style = TextStyle(fontStyle = FontStyle.Italic, fontSize = 16.sp)
        )
        Image(
            modifier = Modifier.padding(bottom = 16.dp),
            painter = painterResource(id = R.drawable.cheese),
            contentDescription = "Beautiful raclette"
        )
        Button(
            onClick = { navController.navigate(NavigationItem.Form.route) }) {
            Text("Let's go", style = TextStyle(fontSize = 16.sp))
        }
    }
}
