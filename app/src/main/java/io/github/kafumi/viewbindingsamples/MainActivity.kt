package io.github.kafumi.viewbindingsamples

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import io.github.kafumi.viewbindingsamples.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var views: ActivityMainBinding

    private val items = ArrayList<Item>()
    private lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        views = DataBindingUtil.setContentView(this, R.layout.activity_main)
        views.activity = this

        adapter = ListAdapter(this, items)

        views.itemsList.adapter = adapter
        views.itemsList.emptyView = views.listEmptyMessage
    }

    @Suppress("unused")
    fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Toast.makeText(this, "You clicked: " + adapter.getItem(position), Toast.LENGTH_SHORT).show()
    }

    @Suppress("unused")
    fun addItem(v: View?) {
        val name = views.newItemName.text.toString()

        if (name.isNotEmpty()) {
            views.newItemName.text.clear()
            items.add(Item(name, System.currentTimeMillis()))
            adapter.notifyDataSetChanged()
        }
    }
}
