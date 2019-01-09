package com.ilgonmic.poll.data

data class User(
    override val id: Id,
    val name: String,
    val items: MutableSet<PollItem> = mutableSetOf()
) : Entity(id)