package com.merkmod.akmepattern.fragments

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.merkmod.akmepattern.common.Navigation

/**
 * Created by thepunkprogrammer on 1/22/18.
 */
abstract class BaseFragment: Fragment(), Navigation {

    override fun getBase() = this.activity ?: Activity()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}