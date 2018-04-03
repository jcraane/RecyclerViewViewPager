package nl.jamiecraane.demo.recyclerviewviewpager

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView


/**
 * Created by jamiecraane on 02/02/2018.
 */
class PagerAdapter(private val items: List<String>) : RecyclerView.Adapter<PagerAdapter.PageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PageViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.view_page, null)
        view.layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        return PageViewHolder(view)
    }

    override fun onBindViewHolder(holder: PageViewHolder?, position: Int) {
        holder?.pageName?.text = items.get(position)
    }

    override fun getItemCount() = items.size

    class PageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pageName: TextView = itemView.findViewById(R.id.name)
    }
}