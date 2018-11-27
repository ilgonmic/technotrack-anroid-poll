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

    private lateinit var viewModel: DistributorViewModel

    override fun onListFragmentInteraction(item: User?) {

    }

    override fun onListFragmentInteraction(item: PollItem?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.distributor_activity)

        this.viewModel = ViewModelProviders.of(this)
            .get(DistributorViewModel::class.java)
    }
}

fun <E> MutableSet<E>.toggle(item: E) {
    if (this.contains(item)) {
        this.remove(item)
    } else {
        this.add(item)
    }
}

fun <E> MutableList<E>.toggle(item: E) {
    if (this.contains(item)) {
        this.remove(item)
    } else {
        this.add(item)
    }
}
