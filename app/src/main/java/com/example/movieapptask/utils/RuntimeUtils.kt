package com.example.movieapptask.utils

import java.text.SimpleDateFormat
import java.util.*


object RuntimeUtils {

    @JvmStatic
    fun formatMinutes(totalMinutes: Int): String {
        var minutes = "${totalMinutes % 60}"
        minutes = if (minutes.length == 1) "0$minutes" else minutes
        return (totalMinutes / 60).toString() + "h " + minutes+"min"
    }

    @JvmStatic
    fun formatAbout(overview : String):String
    {
        val aboutList = overview.split(".")
        if (aboutList.isNotEmpty()){
            return aboutList[0] +"."
        }
        return ""
    }

    @JvmStatic
    fun formatDateString(date : String):String
    {
        val parser = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.US)
        return formatter.format(parser.parse(date))
    }

    @JvmStatic
    fun formatRate(rate : String):String
    {
        return rate.replace(".",",")

    }

    @JvmStatic
    fun getYear(date : String):String
    {
        return date.split("-")[0]

    }
}