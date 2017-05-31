package io.github.kafumi.viewbindingsamples

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

data class Item(val name: String, val time: Long)

class ListAdapter(context: Context, val items: List<Item>) : BaseAdapter() {
    private val inflater = LayoutInflater.from(context)
    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)

    override fun getCount() = items.size
    override fun getItem(position: Int) = items[position]
    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, savedView: View?, parent: ViewGroup): View {
        var view = savedView

        val holder: ViewHolder
        if (view != null) {
            holder = view.tag as ViewHolder
        } else {
            view = inflater.inflate(android.R.layout.simple_list_item_2, parent, false)
            holder = ViewHolder(view)
            view!!.tag = holder
        }

        val item = getItem(position)

        holder.text1.text = item.name
        holder.text2.text = dateFormatter.format(Date(item.time))

        return view
    }

    internal class ViewHolder(view: View) {
        val text1 = view.findViewById(android.R.id.text1) as TextView
        val text2 = view.findViewById(android.R.id.text2) as TextView
    }
}
