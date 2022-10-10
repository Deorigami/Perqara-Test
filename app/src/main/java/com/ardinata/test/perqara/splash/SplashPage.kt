package com.ardinata.test.perqara.splash

import android.content.Intent
import android.util.Log
import android.view.View
import com.ardinata.feature_dashboard.DashboardLandingContract
import com.ardinata.feature_dashboard.landing.DashboardLandingActivity
import com.ardinata.test.perqara.R
import com.ardinata.test.perqara.core.base.BaseViewBindingFragment
import com.ardinata.test.perqara.core.contract.RouterContract
import com.ardinata.test.perqara.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashPage(
    override val layout: Int = R.layout.fragment_main
) : BaseViewBindingFragment<FragmentMainBinding>() {

    @Inject override lateinit var router: DashboardLandingContract.Router

    override fun initBinding(view: View) {
        binding = FragmentMainBinding.bind(view)
    }

    override fun didMount(view: View) {
        super.didMount(view)
    }
}