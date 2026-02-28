package com.suleymanuysal.movion.core.core_common

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun String.toUIFormatDate(): String{
    return try {
        val parsedDate = LocalDate.parse(this,
            DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        parsedDate.format(DateTimeFormatter.ofPattern("MMM dd", Locale.ENGLISH))
    }catch (e: Exception){
        this
    }
}