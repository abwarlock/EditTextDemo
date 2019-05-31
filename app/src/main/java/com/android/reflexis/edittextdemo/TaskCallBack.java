package com.android.reflexis.edittextdemo;

import android.text.SpannableString;

public interface TaskCallBack {
     void onPreExecute();

     String getText();

     void onPostExecute(SpannableString spannableString);
}
