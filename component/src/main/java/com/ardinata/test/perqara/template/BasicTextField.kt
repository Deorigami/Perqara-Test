package com.ardinata.test.perqara.template

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.text.Editable
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import com.ardinata.component.R
import com.ardinata.component.databinding.BasicOutlinedTextFieldBinding

class BasicTextField(
    context: Context,
    attributeSet: AttributeSet? = null
) : LinearLayout(context, attributeSet) {
    private val binding = BasicOutlinedTextFieldBinding.inflate(LayoutInflater.from(context), this, true)
    private val TAG = this::class.java.simpleName

    val textField: EditText = binding.textFieldView

    var noticeText = ""
        set(value) {
            field = value
            refreshView()
        }

    // --------------------------------------------------------------------------------
    // 
    // --------------------------------------------------------------------------------

    var isError = false
        set(value) {
            field = value
            if (value) {
                setLinerColor = R.color.rouge
                binding.noticeTextView.setTextColor(ContextCompat.getColor(context, R.color.rouge))
            } else {
                setLinerColor = R.color.cloudyBlue
                binding.noticeTextView.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.cloudyBlue
                    )
                )
            }
        }

    // --------------------------------------------------------------------------------
    // 
    // --------------------------------------------------------------------------------

    var hasRightIcon = false
        set(value) {
            field = value
            refreshView()
        }

    // --------------------------------------------------------------------------------
    // 
    // --------------------------------------------------------------------------------

    var onRightIconPressed: (() -> Unit)? = null
        set(value) {
            field = value
            refreshView()
        }

    // --------------------------------------------------------------------------------
    //
    // --------------------------------------------------------------------------------

    var hintText = ""
        set(value) {
            field = value
            refreshView()
        }

    // --------------------------------------------------------------------------------
    //
    // --------------------------------------------------------------------------------

    var iconSource: Any? = null
        set(value) {
            field = value
            refreshView()
        }

    // --------------------------------------------------------------------------------
    //
    // --------------------------------------------------------------------------------

    var onTextFieldPressed: (() -> Unit)? = null
        set(value) {
            field = value
            refreshView()
        }

    // --------------------------------------------------------------------------------
    //
    // --------------------------------------------------------------------------------

    var setLinerColor: Int? = R.color.cloudyBlue
        set(value) {
            field = value
            refreshView()
        }

    var onTextChange: ((Editable?) -> Unit)? = null
        set(value) {
            field = value
            refreshView()
        }

    var noticeTextColor = R.color.battleshipGrey
        set(value) {
            field = value
            refreshView()
        }


    init {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.BasicTextField)
        typedArray.run {
            hintText = getString(R.styleable.RadioButtonWithText_title) ?: ""
        }
        typedArray.recycle()
        binding.textFieldView.apply {
            doAfterTextChanged { text ->
                onTextChange?.invoke(text)
                if (text?.isNotEmpty() == true && !isError && isFocused) {
                    setLinerColor = R.color.brightSkyBlue
                }
            }
            setOnFocusChangeListener { _, b ->
                when {
                    b && (this.text?.isNotEmpty() == true) && (!isError) -> setLinerColor =
                        R.color.brightSkyBlue
                    !b && (this.text?.isNotEmpty() == true) && (!isError) -> setLinerColor =
                        R.color.cloudyBlue
                    !b && (this.text.isNullOrEmpty()) && !isError -> setLinerColor =
                        R.color.cloudyBlue
                    b && !isError -> setLinerColor = R.color.brightSkyBlue
                }

            }
        }
    }


    private fun refreshView() {
        handleNoticeText()
        handleLinerColor()
        binding.apply {
            textInputLayout.hint = hintText
        }

        onTextFieldPressed?.let { fn ->
            binding.textFieldView.setOnClickListener {
                fn.invoke()
            }
            binding.textFieldView.inputType = InputType.TYPE_NULL
            binding.textFieldView.isFocusable = false
        }
    }

    private fun handleLinerColor() {
        if (setLinerColor != null) {
            try {
                binding.linerView.background =
                    ColorDrawable(ContextCompat.getColor(context, setLinerColor!!))
            } catch (e: Exception) {
            }
        }
    }

    private fun handleNoticeText() {
        if (noticeText.isNotEmpty()) {
            binding.noticeTextView.apply {
                visibility = View.VISIBLE
                text = noticeText
            }
        } else {
            binding.noticeTextView.visibility = View.GONE
        }
    }
}