package com.technicaltest.myyummyform.navigation

import kotlinx.serialization.Serializable

@Serializable
internal object Home

@Serializable
internal object Form

@Serializable
data class Success(val textFile: String)