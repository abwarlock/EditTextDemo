package com.android.reflexis.edittextdemo

import android.graphics.Color
import android.text.style.ForegroundColorSpan
import java.util.regex.Pattern

object PatternHandler {
    private var allCapsPattern = Pattern.compile("[A-Z]+")
    private var allSmallPattern = Pattern.compile("[a-z]+")
    private var startCapsPattern = Pattern.compile("[A-Z]\\w+")
    private var allNumPattern = Pattern.compile("[0-9]+")
    private var allpunstionPattern = Pattern.compile("[.?!,;:\\-()\\[\\]{}]")


    fun getSpanText(paramString: String): ForegroundColorSpan? {
        return when {
            allCapsPattern.matcher(paramString).matches() -> ForegroundColorSpan(Color.RED)
            allSmallPattern.matcher(paramString).lookingAt() -> ForegroundColorSpan(Color.MAGENTA)
            startCapsPattern.matcher(paramString).lookingAt() -> ForegroundColorSpan(Color.BLUE)
            allNumPattern.matcher(paramString).lookingAt() -> ForegroundColorSpan(Color.GREEN)
            allpunstionPattern.matcher(paramString).lookingAt() -> ForegroundColorSpan(Color.YELLOW)
            else -> ForegroundColorSpan(Color.BLACK)
        }
    }


    fun getSpanText(
        paramString: String,
        startIndex: Int,
        endIndex: Int
    ): Triple<ForegroundColorSpan?, Int, Int> {
        val colorSpan: ForegroundColorSpan? = when {
            allCapsPattern.matcher(paramString).matches() -> ForegroundColorSpan(Color.RED)
            allSmallPattern.matcher(paramString).lookingAt() -> ForegroundColorSpan(Color.MAGENTA)
            startCapsPattern.matcher(paramString).lookingAt() -> ForegroundColorSpan(Color.BLUE)
            allNumPattern.matcher(paramString).lookingAt() -> ForegroundColorSpan(Color.GREEN)
            allpunstionPattern.matcher(paramString).lookingAt() -> ForegroundColorSpan(Color.YELLOW)
            else -> ForegroundColorSpan(Color.BLACK)
        }
        return Triple(colorSpan, startIndex, endIndex)
    }
}