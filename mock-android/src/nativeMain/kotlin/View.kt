// SPDX-License-Identifier: Apache-2.0

package android

open class View(val ctx: Context? = null) {
    var id: Int = 0
    val tags = mutableMapOf<Int, Any>()

    fun getContext(): Context {
        return ctx!!
    }

    fun getTag(key: Int): Any {
        return tags.getValue(key)
    }

    fun setTag(key: Int, tag: Any?) {
        if (tag == null) {
            tags.remove(key)
        } else {
            tags[key] = tag
        }
    }

    fun performClick() {

    }
}
