package com.android.reflexis.edittextdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.*
import android.util.Log
import android.view.View
import android.widget.LinearLayout

class MainActivity : AppCompatActivity(), PasteEditTextListener {

    lateinit var editText: CustomEditText
    lateinit var progressLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById<CustomEditText>(R.id.edit_query)
        progressLayout = findViewById<LinearLayout>(R.id.progress)

        editText.setListener(this)

        editText.addTextChangedListener(object : TextWatcher {
            var isChanging = false;

            override fun onTextChanged(cs: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
                Log.d("", "")
            }

            override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
                Log.d("", "")
            }

            override fun afterTextChanged(arg0: Editable) {
                val string = arg0.toString()
                if (string.endsWith(" ") && !TextUtils.isEmpty(string.trim())) {
                    if (!isChanging) {
                        isChanging = true
                        val spann = SpannableString(editText.text!!.trimEnd())
                        val spannSpace = SpannableString(" ")
                        var spaceIndex = spann.lastIndexOf(" ", spann.length, false)

                        if (spaceIndex == -1) {
                            spaceIndex = 0
                        }

                        if (spaceIndex != 0) {
                            spaceIndex += 1
                        }
                        val spanText = PatternHandler.getSpanText(
                            spann.substring(
                                spaceIndex,
                                if (spann.length == 1) spann.length else spann.length - 1
                            ), spaceIndex, spann.length
                        )

                        if (spanText.first != null) {
                            spann.setSpan(
                                spanText.first,
                                spanText.second,
                                spanText.third,
                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                            )
                            editText.setText(TextUtils.concat(spann, spannSpace))
                            editText.setSelection(editText.length())
                        }
                        isChanging = false
                    }
                }
            }

        })

    }

    override fun onUpdate() {

        StringChangeTask(object : TaskCallBack {
            override fun onPreExecute() {
                progressLayout.visibility = View.VISIBLE
            }

            override fun getText() = editText.text.toString()

            override fun onPostExecute(spannableString: SpannableString?) {
                progressLayout.visibility = View.GONE
                editText.setText("")
                editText.setText(spannableString) // TextView
                editText.setSelection(editText.length())
            }

        }).execute()
    }

}
