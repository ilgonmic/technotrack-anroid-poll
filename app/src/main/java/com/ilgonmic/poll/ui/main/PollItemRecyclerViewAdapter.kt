package com.ilgonmic.poll.ui.main


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ilgonmic.poll.R
import com.ilgonmic.poll.data.PollItem
import com.ilgonmic.poll.ui.main.PollItemFragment.ModeChangedListener
import kotlinx.android.synthetic.main.fragment_pollitem.view.*

class PollItemRecyclerViewAdapter(
    private val mListener: ModeChangedListener?
) : RecyclerView.Adapter<PollItemRecyclerViewAdapter.ViewHolder>() {

    val mValues: MutableList<SelectableItem<PollItem>> = mutableListOf()
    private val mOnClickListener: View.OnClickListener

    private val mutex: Mutex = Mutex()
    var mode: Mode = Mode.DEFAULT
        set(value) {
            if (field != value) {
                field = value
                mListener?.onPollModeChanged(value)
            }
        }

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as SelectableItem<*>
            if (item.selected) {
                mutex.lock()
            } else {
                mutex.unlock()
            }

            mode = if (mutex.isLock()) {
                Mode.ACTIVE
            } else {
                Mode.DEFAULT
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_pollitem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mContentView.text = item.entity.value

        with(holder.mView) {
            tag = item
            isSelected = item.selected

            if (item.selected) {
                mutex.lock()
            }

            if (mutex.isLock()) {
                mode = Mode.ACTIVE
            } else {
                mode = Mode.DEFAULT
            }

            setOnClickListener { v ->
                item.selected = !item.selected
                isSelected = item.selected

                mOnClickListener.onClick(v)
            }
        }
    }

    override fun getItemCount(): Int = mValues.size

    fun reset() {
        mode = Mode.DEFAULT
        mValues.forEach { value -> value.selected = false }
        mutex.free()
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mContentView: TextView = mView.content

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
