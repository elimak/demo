package com.elimak.demo.core.data

import kotlinx.coroutines.channels.ReceiveChannel

interface IRepository {
    val broadcast: ReceiveChannel<ISignal<*>>
}