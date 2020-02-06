// SPDX-License-Identifier: Apache-2.0

package androidx.compose

private class SDL_Recomposer : Recomposer() {
    override fun scheduleChangesDispatch() {}
    override fun hasPendingChanges(): Boolean = false
    override fun recomposeSync() {}
}

internal actual fun createRecomposer(): Recomposer {
    return SDL_Recomposer()
}
