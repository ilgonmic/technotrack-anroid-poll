package com.ilgonmic.poll.ui.main


import android.support.v7.widget.RecyclerView
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ilgonmic.poll.R
import com.ilgonmic.poll.data.PollItem
import com.ilgonmic.poll.ui.main.PollItemFragment.OnListFragmentInteractionListener
import kotlinx.android.synthetic.main.fragment_pollitem.view.*

class PollItemRecyclerViewAdapter(
    private val mValues: List<PollItem>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<PollItemRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as PollItem
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_pollitem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mContentView.text = item.value

        mValues
            .forEach { holder.selectedItems.put(position, it.selected) }

        with(holder.mView) {
            tag = item
            isSelected = item.selected

            setOnClickListener { v ->
                if (holder.selectedItems.get(holder.adapterPosition, false)) {
                    holder.selectedItems.delete(holder.adapterPosition)
                    v.isSelected = false
                } else {
                    holder.selectedItems.put(holder.adapterPosition, true)
                    v.isSelected = true
                }

                item.selected = !item.selected

                mOnClickListener.onClick(v)
            }
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mContentView: TextView = mView.content

        val selectedItems = SparseBooleanArray()

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
