package com.example.movieapptask.utils

import android.text.SpannableStringBuilder
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.core.text.color
import androidx.databinding.BindingAdapter
import com.example.movieapptask.R


@BindingAdapter(value = ["genre","duration","date"], requireAll = true)
fun TextView.appendTextView(genre: String, duration: String, date: String) {
    val appendText = SpannableStringBuilder()
        .color(getColor(context, R.color.white)) { append(genre) }
        .color(getColor(context, R.color.white)) { append("| ") }
        .color(getColor(context, R.color.white)) { append(duration) }
        .color(getColor(context, R.color.white)) { append("| ") }
        .color(getColor(context, R.color.white)) { append(date) }
    this.text = appendText
}


