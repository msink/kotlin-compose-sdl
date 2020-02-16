// SPDX-License-Identifier: Apache-2.0

package android

abstract class ViewGroup(ctx: Context? = null) : View(ctx) {
    private val children = mutableListOf<View>()

    fun addView(child: View, index: Int) {
        children.add(index, child)
    }

    fun getChildAt(index: Int): View {
        return children[index]
    }

    fun removeViewAt(index: Int) {
        children.removeAt(index)
    }

    fun removeViews(start: Int, count: Int) {
        repeat(count) { index ->
            children.removeAt(start + index)
        }
    }

    fun removeAllViews() {
        children.clear()
    }

    fun <T : View?> findViewById(id: Int): T {
        return children.firstOrNull() { it.id == id } as T
    }
}
