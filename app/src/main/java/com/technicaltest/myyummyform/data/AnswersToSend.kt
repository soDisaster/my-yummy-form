package com.technicaltest.myyummyform.data

class AnswersToSend : ArrayList<AnswersToSendItem>(mutableListOf())

data class AnswersToSendItem(
    val id: Int,
    val choices: List<Int>
)