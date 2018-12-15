package com.ilgonmic.poll

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ilgonmic.poll.ui.main.DistributorViewModel
import com.ilgonmic.poll.ui.main.Mode
import com.ilgonmic.poll.ui.main.PollItemFragment
import com.ilgonmic.poll.ui.main.UserFragment
import kotlinx.android.synthetic.main.distributor_activity.*

class DistributorActivity : AppCompatActivity(),
    UserFragment.ModeChangedListener,
    PollItemFragment.ModeChangedListener {

    private lateinit var userMode: Mode
    private lateinit var pollMode: Mode

    override fun onUserModeChanged(value: Mode) {
        userMode = value
        if (value == Mode.DEFAULT && pollMode == Mode.DEFAULT) {
            calculate_button.text = getString(R.string.calculate)
        } else {
            calculate_button.text = getString(R.string.Add)
        }
    }

    override fun onPollModeChanged(value: Mode) {
        pollMode = value
        if (value == Mode.DEFAULT && userMode == Mode.DEFAULT) {
            calculate_button.text = getString(R.string.calculate)
        } else {
            calculate_button.text = getString(R.string.Add)
        }
    }

    private lateinit var viewModel: DistributorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.distributor_activity)

        userMode = savedInstanceState?.get("user_mode") as? Mode ?: Mode.DEFAULT
        pollMode = savedInstanceState?.get("poll_mode") as? Mode ?: Mode.DEFAULT

        this.viewModel = ViewModelProviders.of(this)
            .get(DistributorViewModel::class.java)
    }


}
