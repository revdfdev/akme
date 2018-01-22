package com.merkmod.akmepattern.akme

import kategory.Either
import kategory.monad
import kotlinx.coroutines.experimental.channels.SendChannel
import kotlinx.coroutines.experimental.runBlocking

        /**
 * Created by thepunkprogrammer on 1/22/18.
 */
typealias Service<A> = Either<AkmeException, A>

sealed class AkmeException: Throwable() {

    data class UiException(val msg: String) : AkmeException()

    data class ApiException(val msg: String) : AkmeException()

    data class CallException(val msg: String) : AkmeException()

}

fun <A> serviceRight(op: () -> A): Either<AkmeException, A> = Either.Right(op())

fun <A> serviceLeft(ex: AkmeException): Either<AkmeException, A> = Either.Left(ex)

fun serviceMonad() = Either.monad<AkmeException>()

fun <A> A.catchOnly(ex: AkmeException): Service<A> = catchOnly(ex) { this }

fun <A> A.catchUi(): Service<A> = this.catchOnly(AkmeException.UiException(""))

fun <A> catchUi(default: () -> A): Service<A> = default().catchOnly(AkmeException.UiException(""))

fun <A> catchOnly(ex: AkmeException, default: () -> A): Service<A> = try {
    serviceRight({ default() })
} catch (e: Throwable) {
    serviceLeft(ex)
}

fun <A> SendChannel<A>.sendBlocking(element: A): Unit = runBlocking {
    send(element)
}
