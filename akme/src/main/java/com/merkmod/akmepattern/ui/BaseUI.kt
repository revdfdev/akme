package com.merkmod.akmepattern.ui

import android.support.design.widget.AppBarLayout
import com.merkmod.akmepattern.R
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.themedToolbar
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.themedAppBarLayout

/**
 * Created by thepunkprogrammer on 1/22/18.
 */
abstract class BaseUI<in T>: AnkoComponent<T> {

    override fun createView(ui: AnkoContext<T>) = with(ui){
        coordinatorLayout {
            themedAppBarLayout {
                id = R.id.app_bar
                themedToolbar(R.style.ThemeOverlay_AppCompat_Dark_ActionBar) {
                    id = R.id.toolbar
                }.lparams {
                    width = matchParent
                    height = dip(48)
                }
            }.lparams {
                width = matchParent
                height = wrapContent
            }

            relativeLayout {
                id = R.id.main_container
            }.lparams {
                width = matchParent
                height = wrapContent
                behavior = AppBarLayout.ScrollingViewBehavior()
            }

            lparams {
                width = matchParent
                height = wrapContent
            }
        }
    }
}