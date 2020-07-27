package com.leehendryp.wtest.core

import android.view.View

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.vanish(duration: Long = 300) {
    animate()
        .alpha(0f)
        .setDuration(duration)
        .withEndAction { gone() }
        .start()
}

fun View.fadeOut(duration: Long = 300) {
    animate()
        .alpha(0f)
        .setDuration(duration)
        .withEndAction { invisible() }
        .start()
}

fun View.fadeIn(duration: Long = 300) {
    animate()
        .alpha(1f)
        .setDuration(duration)
        .withStartAction { visible() }
        .start()
}