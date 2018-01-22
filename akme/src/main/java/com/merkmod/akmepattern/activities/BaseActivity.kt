package com.merkmod.akmepattern.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.ajalt.timberkt.Timber
import com.merkmod.akmepattern.common.Navigation

/**
 * Created by thepunkprogrammer on 1/22/18.
 */
abstract class BaseActivity: AppCompatActivity(), Navigation {

    override fun getBase() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) Timber.d { "Save Instance is not null" }
        Timber.d { "Activity OnCreate" }
    }

    override fun onStart() {
        super.onStart()
        Timber.d { "Activity OnStart" }
    }

    override fun onStop() {
        super.onStop()
        Timber.d { "Activity OnStop" }
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d { "Activity OnStop" }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Timber.d { "Activity onResult" }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Timber.d { "Save instance state" }
    }
}