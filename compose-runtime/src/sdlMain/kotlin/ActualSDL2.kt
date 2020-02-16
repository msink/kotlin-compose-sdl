// SPDX-License-Identifier: Apache-2.0

package androidx.compose

// TODO(b/137794549): Remove View System-related expect/actuals
actual typealias View = android.View

// TODO(b/137794549): Remove View System-related expect/actuals
actual val View.context: Context
    get() = getContext()

// TODO(b/137794549): Remove View System-related expect/actuals
actual typealias ViewGroup = android.ViewGroup

// TODO(b/137794549): Remove View System-related expect/actuals
actual typealias Context = android.Context

// TODO(b/137794558): Create portable abstraction for scheduling
actual class Looper() //TODO typealias android.os.Looper

// TODO(b/137794558): Create portable abstraction for scheduling
actual object LooperWrapper {
    actual fun getMainLooper(): Looper = TODO() //android.os.Looper.getMainLooper()
}

actual fun isMainThread(): Boolean {
    return true //TODO android.os.Looper.myLooper() == android.os.Looper.getMainLooper()
}

// TODO(b/137794558): Create portable abstraction for scheduling
actual class Handler actual constructor(looper: Looper) {
    actual fun postAtFrontOfQueue(block: () -> Unit): Boolean { TODO() }
}

// TODO(b/137794558): Create portable abstraction for scheduling
actual interface ChoreographerFrameCallback {
    actual fun doFrame(frameTimeNanos: Long)
}

actual object Choreographer {
    actual fun postFrameCallback(callback: ChoreographerFrameCallback) { TODO() }
    actual fun postFrameCallbackDelayed(delayMillis: Long, callback: ChoreographerFrameCallback) { TODO() }
    actual fun removeFrameCallback(callback: ChoreographerFrameCallback) { TODO() }
}

actual object Trace {
    actual fun beginSection(name: String) {} //TODO android.os.Trace.beginSection(name)
    actual fun endSection() {} //TODO android.os.Trace.endSection()
}
