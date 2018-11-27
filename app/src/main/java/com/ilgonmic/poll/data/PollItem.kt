package com.ilgonmic.poll.data

data class PollItem(
    override val id: Id,
    val value: String,
    var selected: Boolean = false
) : Entity(id)