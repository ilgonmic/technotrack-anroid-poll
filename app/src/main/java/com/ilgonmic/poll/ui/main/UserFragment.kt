package com.ilgonmic.poll.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ilgonmic.poll.R
import com.ilgonmic.poll.data.Entity
import com.ilgonmic.poll.data.User

class UserFragment : Fragment() {

    private var listener: OnListFragmentInteractionListener? = null

    private lateinit var viewModel: DistributorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.viewModel = ViewModelProviders.of(this)
            .get(DistributorViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_list, container, false)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = UserRecyclerViewAdapter(listener)
                    .apply {
                        viewModel.getUsers()
                            .observe(this@UserFragment, Observer<List<SelectableItem<User>>> { items ->
                                this.mValues.clear()
                                this.mValues.addAll(items ?: emptyList())
                            })
                    }
            }
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: SelectableItem<Entity>?)
    }
}
