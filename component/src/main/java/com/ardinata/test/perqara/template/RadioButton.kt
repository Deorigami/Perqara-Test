package com.ardinata.test.perqara.template

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.ardinata.component.databinding.RadioButtonBinding

class RadioButton(
    context: Context,
    attributeSet: AttributeSet? = null
) : LinearLayout(context, attributeSet){

    private val binding = RadioButtonBinding.inflate(LayoutInflater.from(context), this, true)

    var radioButtonIsSelected = false
        set(value) {
            field = value
            refreshView()
        }

    var onRadioButtonChange : ((Boolean) -> Unit)? = null

    init {
        binding.root.setOnClickListener {
            radioButtonIsSelected = !radioButtonIsSelected
            onRadioButtonChange?.invoke(radioButtonIsSelected)
        }
    }

    private fun refreshView(){
        binding.radioIndicator.isSelected = radioButtonIsSelected
    }

    fun disableOnClick(){
        binding.root.apply {
            setOnClickListener {}
            isClickable = false
            isFocusable = false
        }
    }

}