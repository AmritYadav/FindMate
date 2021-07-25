package com.amydv.findmate.utils

import android.view.View

fun View.setVisible(flag: Boolean) {
    visibility = if (flag) View.VISIBLE else View.GONE
}