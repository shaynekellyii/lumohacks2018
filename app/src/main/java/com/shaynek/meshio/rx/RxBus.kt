package com.shaynek.meshio.rx

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Simple singleton event bus implemented using RxJava
 */
object RxBus {

    val publisher = PublishSubject.create<Any>()

    fun publish(event: Any) {
        publisher.onNext(event)
    }

    fun <T> listen(eventType: Class<T>): Observable<T> = publisher.ofType(eventType)
}