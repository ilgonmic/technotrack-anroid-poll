package com.ilgonmic.poll

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ilgonmic.poll.data.PollItem
import com.ilgonmic.poll.data.User
import com.ilgonmic.poll.ui.main.DistributorViewModel
import com.ilgonmic.poll.ui.main.PollItemFragment
import com.ilgonmic.poll.ui.main.UserFragment

class DistributorActivity : AppCompatActivity(),
    UserFragment.OnListFragmentInteractionListener,
    PollItemFragment.OnListFragmentInteractionListener {

    override fun onListFragmentInteraction(item: User?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onListFragmentInteraction(item: PollItem?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val viewModel = ViewModelProviders.of(this)
            .get(DistributorViewModel::class.java)
    }

}
