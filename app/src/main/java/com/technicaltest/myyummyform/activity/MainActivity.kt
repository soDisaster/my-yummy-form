package com.technicaltest.myyummyform.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.technicaltest.myyummyform.composable.YummyTopAppBar
import com.technicaltest.myyummyform.navigation.Form
import com.technicaltest.myyummyform.navigation.Home
import com.technicaltest.myyummyform.navigation.Success
import com.technicaltest.myyummyform.screen.FormScreen
import com.technicaltest.myyummyform.screen.HomeScreen
import com.technicaltest.myyummyform.screen.SuccessScreen
import com.technicaltest.myyummyform.ui.theme.MyYummyFormTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val scaffoldState = remember { SnackbarHostState() }

            MyYummyFormTheme {
                Scaffold(
                    topBar = { YummyTopAppBar() },
                    snackbarHost = {
                        SnackbarHost(scaffoldState)
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        val navController = rememberNavController()
                        NavHost(
                            navController = navController,
                            startDestination = Home
                        ) {
                            composable<Home> {
                                HomeScreen(navController)
                            }
                            composable<Form> {
                                FormScreen(navController, viewModel, scaffoldState)
                            }
                            composable<Success> {
                                val details =
                                    it.toRoute<Success>()
                                SuccessScreen(navController, details.textFile)
                            }
                        }
                    }
                }
            }
        }
    }
}