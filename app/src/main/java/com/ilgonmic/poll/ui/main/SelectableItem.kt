package com.ilgonmic.poll.ui.main

import com.ilgonmic.poll.data.Entity


data class SelectableItem<out T : Entity>(
    val entity: T,
    var selected: Boolean
)