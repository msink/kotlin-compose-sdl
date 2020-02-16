// SPDX-License-Identifier: Apache-2.0

package android

open class Activity: Context() {
    class Bundle

    open fun onCreate(savedInstanceState: Bundle?) {
    }

    fun dispose() {
    }

    fun setContentView(view: View) {
    }
}
