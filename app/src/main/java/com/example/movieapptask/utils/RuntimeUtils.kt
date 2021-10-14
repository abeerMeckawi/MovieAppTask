package com.example.movieapptask.utils

object RuntimeUtils {

    @JvmStatic
    fun formatMinutes(totalMinutes: Int): String {
        var minutes = "${totalMinutes % 60}"
        minutes = if (minutes.length == 1) "0$minutes" else minutes
        return (totalMinutes / 60).toString() + "h " + minutes+"min"
    }
}