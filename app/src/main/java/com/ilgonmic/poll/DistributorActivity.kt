package com.ilgonmic.poll

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.ilgonmic.poll.ui.main.DistributorViewModel
import com.ilgonmic.poll.ui.main.Mode
import com.ilgonmic.poll.ui.main.PollItemFragment
import com.ilgonmic.poll.ui.main.UserFragment
import kotlinx.android.synthetic.main.distributor_activity.*

class DistributorActivity : AppCompatActivity(),
    UserFragment.ModeChangedListener,
    PollItemFragment.ModeChangedListener {

    private lateinit var viewModel: DistributorViewModel

    private var userMode: Mode = Mode.DEFAULT
        set(value) {
            field = value
            changeMode(value, pollMode)
        }

    private var pollMode: Mode = Mode.DEFAULT
        set(value) {
            field = value
            changeMode(value, userMode)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.distributor_activity)

        userMode = if (savedInstanceState?.getBoolean(USER_MODE) != false) Mode.DEFAULT else Mode.ACTIVE
        pollMode = if (savedInstanceState?.getBoolean(POLL_MODE) != false) Mode.DEFAULT else Mode.ACTIVE

        this.viewModel = ViewModelProviders.of(this)
            .get(DistributorViewModel::class.java)
    }

    override fun onUserModeChanged(value: Mode) {
        userMode = value
        changeMode(value, pollMode)
    }

    override fun onPollModeChanged(value: Mode) {
        pollMode = value
        changeMode(value, userMode)
    }

    private fun changeMode(value: Mode, another: Mode) {
        if (value == Mode.DEFAULT && another == Mode.DEFAULT) {
            calculate_button.text = getString(R.string.calculate)
        } else {
            calculate_button.text = getString(R.string.Add)
            calculate_button.setOnClickListener { createAddButtonHandler() }
        }
    }

    private fun createAddButtonHandler() {
        Log.i("activity", "click")
        viewModel.changeMode(Mode.DEFAULT)
        viewModel.changeMode(Mode.ACTIVE)

        viewModel.getUsers()
            .value
            ?.filter { it.selected }
            ?.forEach {
                val items = viewModel
                    .getItems()
                    .value
                    ?.filter { it.selected }
                    ?.map { it.entity }
                    ?: emptyList()

                it.entity.items.addAll(items)
            }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        if (outState == null) {
            return
        }

        outState.putBoolean(USER_MODE, userMode == Mode.DEFAULT)
        outState.putBoolean(POLL_MODE, pollMode == Mode.DEFAULT)
    }

    companion object {
        private val USER_MODE = "user_mode"
        private val POLL_MODE = "poll_mode"
    }
}
