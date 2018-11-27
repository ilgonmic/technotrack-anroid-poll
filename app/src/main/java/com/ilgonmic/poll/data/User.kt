package com.ilgonmic.poll.data

data class User(
    override val id: Id,
    val name: String,
    var selected: Boolean = false
) : Entity(id)