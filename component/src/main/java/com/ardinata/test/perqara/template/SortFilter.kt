package com.ardinata.test.perqara.template

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.ardinata.component.databinding.SortingFilterBinding

class SortFilter(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs){
    private val binding = SortingFilterBinding.inflate(LayoutInflater.from(context), this, true)

    var sortChangeListener : (SortType) -> Unit = {}
        set(value){
            field = value
            refreshView()
        }

    var initialSortType : SortType = SortType.ASCENDING
        set(value){
            field = value
            binding.ascSort.isActive = value == SortType.ASCENDING
            binding.dscSort.isActive = value == SortType.DESCENDING
            sortChangeListener.invoke(value)
        }


    private fun refreshView() {
        binding.ascSort.apply {
            onPressed = {
                binding.dscSort.isActive = false
                isActive = true
                sortChangeListener.invoke(SortType.ASCENDING)
            }
        }
        binding.dscSort.apply {
            onPressed = {
                binding.ascSort.isActive = false
                isActive = true
                sortChangeListener.invoke(SortType.DESCENDING)
            }
        }
    }


    companion object {
        enum class SortType{
            ASCENDING, DESCENDING
        }
    }
}