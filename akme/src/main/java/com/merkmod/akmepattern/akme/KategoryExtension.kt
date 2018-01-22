package com.merkmod.akmepattern.akme

import kategory.ListKW
import kategory.Option

/**
 * Created by thepunkprogrammer on 1/22/18.
 */
fun <A> A?.toOption(): Option<A> = if (this != null) Option.pure(this) else Option.empty()

fun <A> ListKW<A>.headOption(): Option<A> = this.firstOrNull().toOption()

fun <A> ListKW<A>.lastOption(): Option<A> = this.lastOrNull().toOption()


