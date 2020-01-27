package com.elimak.demo.core.data

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.ReceiveChannel

@ExperimentalCoroutinesApi
open class RepositoryBase : IRepository {
    private val publisher = ConflatedBroadcastChannel<ISignal<*>>()
    override val broadcast: ReceiveChannel<ISignal<*>>
        get() = publisher.openSubscription()
}