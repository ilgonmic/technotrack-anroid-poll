package com.ilgonmic.poll

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ilgonmic.poll.data.Entity
import com.ilgonmic.poll.ui.main.DistributorViewModel
import com.ilgonmic.poll.ui.main.PollItemFragment
import com.ilgonmic.poll.ui.main.SelectableItem
import com.ilgonmic.poll.ui.main.UserFragment

class DistributorActivity : AppCompatActivity(),
    UserFragment.OnListFragmentInteractionListener,
    PollItemFragment.OnListFragmentInteractionListener {

    private lateinit var viewModel: DistributorViewModel

    override fun onListFragmentInteraction(item: SelectableItem<Entity>?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.distributor_activity)

        this.viewModel = ViewModelProviders.of(this)
            .get(DistributorViewModel::class.java)
    }
}
