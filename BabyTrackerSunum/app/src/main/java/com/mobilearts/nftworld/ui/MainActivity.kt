package com.mobilearts.nftworld.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobilearts.nftworld.R
import com.revenuecat.purchases.Purchases
import com.revenuecat.purchases.PurchasesConfiguration

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Purchases.debugLogsEnabled = true
        Purchases.configure(PurchasesConfiguration.Builder(this, "goog_KvZGtHOumaYIINuuEzcMwAxaAwF").build())
    }
}