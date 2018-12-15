package com.ilgonmic.poll.ui.main

class Mutex {
    private var count = 0

    fun lock() {
        count++
    }

    fun unlock() {
        if (count < 0) {
            count = 0
        } else {
            count--
        }
    }

    fun isLock(): Boolean {
        return count > 0
    }
}