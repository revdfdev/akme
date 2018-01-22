package com.merkmod.akmepattern.common

import android.app.Activity
import android.content.Intent
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.merkmod.akmepattern.akme.catchUi

/**
 * Created by thepunkprogrammer on 1/22/18.
 */
interface Navigation {

    fun getBase(): Activity

    fun goTo(clz: Activity) = catchUi {
        val i = Intent(getBase(), clz::class.java)
        getBase().startActivity(i)
    }

    fun change(@IdRes container: Int, clz: Fragment) = catchUi {
        val fragmentTransaction = (getBase() as AppCompatActivity).supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(container, clz)
        fragmentTransaction.commit()
    }

    fun goBack() {
        getBase().onBackPressed()
    }
}