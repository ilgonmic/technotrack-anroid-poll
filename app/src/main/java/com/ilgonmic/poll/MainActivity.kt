package com.ilgonmic.poll

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ilgonmic.poll.ui.main.PollItemFragment
import com.ilgonmic.poll.ui.main.UserFragment
import com.ilgonmic.poll.ui.main.dummy.DummyContent

class MainActivity : AppCompatActivity(),
    UserFragment.OnListFragmentInteractionListener,
    PollItemFragment.OnListFragmentInteractionListener {
    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

}
