package com.ardinata.feature_dashboard

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.ardinata.test.perqara.core.contract.RouterContract

interface DashboardLandingContract {
    interface Router : RouterContract {
        fun navigateToDashboard(context: Context)
        fun navigateToGameDetail(context: Context, id: Long)
    }
}