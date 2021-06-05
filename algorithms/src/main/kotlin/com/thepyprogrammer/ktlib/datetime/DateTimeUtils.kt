package com.thepyprogrammer.ktlib.datetime

import java.text.SimpleDateFormat
import java.time.*
import java.time.format.DateTimeFormatterBuilder

val dTF =
    DateTimeFormatterBuilder()
        .parseCaseInsensitive()
        .appendPattern("dd/MM/yyyy")
        .toFormatter()

val dateFormat = SimpleDateFormat("dd/MM/yyyy")

fun LocalDateTime.onDate(date: LocalDate) =
    dTF.format(toLocalDate()).equals(dTF.format(date))

fun LocalDateTime.atTime(time: LocalTime) = run {
    val itTime = toLocalTime()
    (itTime.hour == time.hour && itTime.minute == time.minute && itTime.second == time.second)
}

fun LocalDateTime.atHour(hour: Int) =
    toLocalTime().hour == hour

fun Long.toLocalDateTime() = LocalDateTime.ofInstant(
    Instant.ofEpochMilli(this),
    ZoneId.systemDefault()
)

fun Long.toLocalDate() = LocalDateTime.ofInstant(
    Instant.ofEpochMilli(this),
    ZoneId.systemDefault()
).toLocalDate()

fun Long.toLocalTime() = LocalDateTime.ofInstant(
    Instant.ofEpochMilli(this),
    ZoneId.systemDefault()
).toLocalTime()

fun LocalDateTime.toEpoch() = atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()



fun buildDTF(pattern: String) = DateTimeFormatterBuilder()
    .parseCaseInsensitive()
    .appendPattern(pattern)
    .toFormatter()



fun LocalDateTime.format(pattern: String) =
    format(buildDTF(pattern))

fun LocalDate.format(pattern: String) =
    format(buildDTF(pattern))

fun LocalTime.format(pattern: String) =
    format(buildDTF(pattern))
