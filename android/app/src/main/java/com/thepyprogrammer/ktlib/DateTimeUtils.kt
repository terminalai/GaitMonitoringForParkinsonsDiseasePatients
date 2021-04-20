package com.thepyprogrammer.ktlib

import android.text.format.DateUtils
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

fun LocalDateTime.onDate(date: LocalDate) =
    Util.dateFormat.format(toLocalDate()).equals(Util.dateFormat.format(date))

fun LocalDateTime.atTime(time: LocalTime) = run {
    val itTime = toLocalTime()
    (itTime.hour == time.hour && itTime.minute == time.minute && itTime.second == time.second)
}

fun LocalDateTime.atHour(hour: Int) =
    toLocalTime().hour == hour
