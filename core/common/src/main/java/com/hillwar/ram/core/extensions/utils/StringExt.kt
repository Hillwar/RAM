package com.hillwar.ram.core.extensions.utils

import androidx.compose.ui.graphics.Color
import com.hillwar.ram.core.theme.StatusAlive
import com.hillwar.ram.core.theme.StatusDead
import com.hillwar.ram.core.theme.StatusUndefined

const val ALIVE = "Alive"
const val DEAD = "Dead"

fun String.getStatusColor(): Color {
    return when (this) {
        ALIVE -> StatusAlive
        DEAD -> StatusDead
        else -> StatusUndefined
    }
}
