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
import com.ilgonmic.poll.data.PollItem

class PollItemFragment : Fragment() {

    private var listener: ModeChangedListener? = null

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
        val view = inflater.inflate(R.layout.fragment_pollitem_list, container, false)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = PollItemRecyclerViewAdapter(listener)
                    .apply {
                        viewModel.getItems()
                            .observe(this@PollItemFragment, Observer<List<SelectableItem<PollItem>>> { items ->
                                //Todo use diff util intead
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
        if (context is ModeChangedListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement ModeChangedListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface ModeChangedListener {
        fun onPollModeChanged(value: Mode)
    }
}
