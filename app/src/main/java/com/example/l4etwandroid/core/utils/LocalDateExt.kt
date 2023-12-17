package com.example.l4etwandroid.core.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Date
import java.util.TimeZone

const val DATE_PATTERN_YYYY_MM_DD = "yyyy-MM-dd"

fun String.toLocalDateOrToday(pattern: String = DATE_PATTERN_YYYY_MM_DD): LocalDate =
    toLocalDate(pattern) { LocalDate.now() }

fun String.toLocalDate(
    pattern: String = DATE_PATTERN_YYYY_MM_DD,
    fallbackSupplier: () -> LocalDate
): LocalDate = toLocalDateOrNull(pattern) ?: fallbackSupplier()

fun String.toLocalDateOrNull(pattern: String = DATE_PATTERN_YYYY_MM_DD): LocalDate? {
    val dtf = DateTimeFormatter.ofPattern(pattern)
    return try {
        LocalDate.parse(this, dtf)
    } catch (e: DateTimeParseException) {
        null
    }
}

fun LocalDate.formatWithPattern(pattern: String = DATE_PATTERN_YYYY_MM_DD): String =
    format(formatter(pattern))

fun Long.toDateString(): String {
    val sdf = SimpleDateFormat(DATE_PATTERN_YYYY_MM_DD, primaryLocale)
    val tz = TimeZone.getDefault()
    val now = Date()
    val offsetFromUtc = tz.getOffset(now.time)
    return sdf.format(this + offsetFromUtc)
}

private fun formatter(pattern: String): DateTimeFormatter =
    DateTimeFormatter.ofPattern(pattern, primaryLocale)