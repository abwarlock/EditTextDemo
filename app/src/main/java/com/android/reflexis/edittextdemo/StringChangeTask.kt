package com.android.reflexis.edittextdemo

import android.os.AsyncTask
import android.text.Spannable
import android.text.SpannableString
import android.text.TextUtils
import java.util.*

class StringChangeTask(var callBack: TaskCallBack) :
    AsyncTask<Void, Void, SpannableString>() {

    override fun onPreExecute() {
        callBack.onPreExecute()
    }

    override fun doInBackground(vararg voids: Void): SpannableString? {
        val arrayList =
            ArrayList(Arrays.asList(*callBack.text.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()))
        var orginalspann = SpannableString("")

        for (value in arrayList) {
            val spann = SpannableString(value)
            val colorSpan = PatternHandler.getSpanText(value)
            spann.setSpan(
                colorSpan,
                0,
                spann.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            orginalspann = SpannableString(TextUtils.concat(orginalspann, " ", spann))
        }

        return orginalspann
    }

    override fun onPostExecute(spannableString: SpannableString) {
        callBack.onPostExecute(spannableString)
    }


}
