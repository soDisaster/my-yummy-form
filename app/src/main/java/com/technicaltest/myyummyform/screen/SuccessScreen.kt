package com.technicaltest.myyummyform.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.technicaltest.myyummyform.navigation.Home

@Composable
fun SuccessScreen(navController: NavHostController, textFile: String) {

    Column {

        Text("Congratulations your file was successufully created!")

        Text(textFile)

        Text("You can find a file with your answers in your Documents")

        Button(onClick = {}) {
            Text("Send by email")
        }

        Button(onClick = { navController.navigate(Home) }) {
            Text("Finish")
        }

    }
}
