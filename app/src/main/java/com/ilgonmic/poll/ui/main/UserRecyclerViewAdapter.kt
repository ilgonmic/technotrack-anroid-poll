package com.ilgonmic.poll.ui.main


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ilgonmic.poll.R
import com.ilgonmic.poll.data.User
import kotlinx.android.synthetic.main.fragment_user.view.*


class UserRecyclerViewAdapter(
    private val mListener: UserFragment.ModeChangedListener?
) : RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder>() {

    val mValues: MutableList<SelectableItem<User>> = mutableListOf()
    private val mOnClickListener: View.OnClickListener

    private val mutex: Mutex = Mutex()
    private var mode: Mode = Mode.DEFAULT
        set(value) {
            if (field != value) {
                field = value
                mListener?.onUserModeChanged(value)
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

            if (mutex.isLock()) {
                mode = Mode.ACTIVE
            } else {
                mode = Mode.DEFAULT
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mContentView.text = item.entity.name

        with(holder.mView) {
            tag = item
            isSelected = item.selected

            setOnClickListener { v ->
                item.selected = !item.selected
                isSelected = item.selected

                mOnClickListener.onClick(v)
            }
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mContentView: TextView = mView.content

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
