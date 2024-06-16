package com.technicaltest.myyummyform.data

class YummyForm : ArrayList<YummyFormItem>()

data class YummyFormItem(
    val choices: List<Choice>,
    val id: Int,
    val multiple: Boolean,
    val name: String,
    val order: Int,
    val question: String
)

data class Choice(
    val description: String?,
    val id: Int,
    val name: String,
    val order: Int
)