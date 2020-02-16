// SPDX-License-Identifier: Apache-2.0

package android

class LinearLayout(ctx: Context? = null): ViewGroup(ctx) {
    var orientation = HORIZONTAL

    companion object {
        const val HORIZONTAL = 1
        const val VERTICAL = 2
    }
}
