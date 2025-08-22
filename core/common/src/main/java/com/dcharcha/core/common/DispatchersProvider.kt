package com.dcharcha.core.common

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

interface DispatchersProvider {
    val io: CoroutineContext
    val main: CoroutineContext
    val default: CoroutineContext
}

class DefaultDispatchers : DispatchersProvider {
    override val io = Dispatchers.IO
    override val main = Dispatchers.Main
    override val default = Dispatchers.Default
}
