package com.ardinata.feature_dashboard.detail

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import com.ardinata.feature_dashboard.R
import com.ardinata.test.perqara.core.base.BaseActivity
import com.ardinata.util.databinding.BaseActivityLayoutBinding

class GameDetailActivity(
    layout: Int = R.layout.base_activity_layout
) : BaseActivity(layout){
    private val binding by lazy { BaseActivityLayoutBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val fragment = GameDetailPage()
        fragment.arguments = intent.extras
        supportFragmentManager.beginTransaction().replace(binding.fragmentHolder.id, fragment).commit()
    }
}