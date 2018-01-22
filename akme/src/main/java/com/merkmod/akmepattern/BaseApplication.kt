package com.merkmod.akmepattern

import android.app.Application
import android.content.pm.ApplicationInfo
import com.merkmod.akmepattern.di.BaseModules
import org.koin.Koin
import org.koin.android.ext.android.startKoin
import org.koin.android.logger.AndroidLogger
import timber.log.Timber

/**
 * Created by thepunkprogrammer on 1/22/18.
 */
fun <APICLASS>Application.createApplication(baseUrl: String?, initKoin: Boolean, initLogger: Boolean) {
    if (initKoin and !baseUrl.isNullOrEmpty()) {
        startKoin(this, BaseModules<APICLASS>(baseUrl!!))
    } else {
        throw Exception("No url specified")
    }

    if (initLogger) {
        val d = 0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE
        if (d) {
            Koin.logger = AndroidLogger()
            Timber.plant(Timber.DebugTree())
        }
    }
}