package com.man.filmku.widget

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputLayout
import com.man.filmku.R
import com.man.filmku.extentions.findIdByLazy

class CustomEditText(context: Context, attributeSet: AttributeSet) :
    LinearLayout(context, attributeSet) {

    private val titleView: TextView by findIdByLazy(R.id.tv_title)
    private val textInputLayout: TextInputLayout by findIdByLazy(R.id.text_input_layout)
    private val mEditText: EditText by findIdByLazy(R.id.edt_text)
    val editText : EditText get() = mEditText

    init {
        inflate(context, R.layout.custom_text_input, this)
        context.obtainStyledAttributes(
            attributeSet, R.styleable.TextInputCustom, 0, 0
        ).apply {
            // Access Values Custom Attrs.xml
            val title = getString(R.styleable.TextInputCustom_title)
            val hintText = getString(R.styleable.TextInputCustom_hint_text)
            val inputType = getInt(R.styleable.TextInputCustom_input_type, 2)
            titleView.text = title
            mEditText.hint = hintText
            mEditText.inputType = when (inputType) {
                0 -> InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                1 -> {
                    setTogglePassword()
                    InputType.TYPE_TEXT_VARIATION_PASSWORD
                }

                else -> InputType.TYPE_CLASS_TEXT
            }

            mEditText.addTextChangedListener {
                textInputLayout.error = null
            }

        }.recycle()
    }

    private fun setTogglePassword() {
        textInputLayout.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
    }

    fun setError(message : String) {
        textInputLayout.error = message
    }
}