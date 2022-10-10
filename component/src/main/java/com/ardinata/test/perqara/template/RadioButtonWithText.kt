package com.ardinata.test.perqara.template

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.ardinata.component.R
import com.ardinata.component.databinding.RadioButtonWithTextBinding

class RadioButtonWithText(
    context: Context,
    attributeSet: AttributeSet? = null
) : LinearLayout(context, attributeSet) {
    private val binding = RadioButtonWithTextBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    var title = ""
        set(value){
            field = value
            refreshView()
        }

    var isActive = false
        set(value){
            field = value
            binding.radioButton.radioButtonIsSelected = isActive
            refreshView()
        }

    var onPressed : () -> Unit = {}
        set(value){
            field = value
            binding.root.setOnClickListener {
                value.invoke()
            }
        }


    init {
        binding.radioButton.disableOnClick()
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.RadioButtonWithText)
        typedArray.run {
            title = getString(R.styleable.RadioButtonWithText_title) ?: ""
        }
        typedArray.recycle()
    }

    private fun refreshView() {
        binding.textView.text = title
    }

}