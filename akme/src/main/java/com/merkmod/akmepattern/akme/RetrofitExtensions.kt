package com.merkmod.akmepattern.akme

import retrofit2.Call
import retrofit2.Retrofit

/**
 * Created by thepunkprogrammer on 1/22/18.
 */
fun <T> Call<T>.toService(): Service<T>  {
    val response= this.execute()

    return if (response.isSuccessful) {
        serviceRight { response?.body()!! }
    } else {
        serviceLeft(AkmeException.ApiException(""))
    }
}