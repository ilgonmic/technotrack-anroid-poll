package com.ilgonmic.poll.data

data class PollItem(
    override val id: Id,
    val value: String
) : Entity(id)