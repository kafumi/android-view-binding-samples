package io.github.kafumi.viewbindingsamples

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.OnItemClick

class MainActivity : AppCompatActivity() {
    @BindView(R.id.items_list)
    internal lateinit var itemsList: ListView

    @BindView(R.id.list_empty_message)
    internal lateinit var listEmptyMessage: TextView

    @BindView(R.id.new_item_name)
    internal lateinit var newItemName: EditText

    private val items = ArrayList<Item>()
    private lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        adapter = ListAdapter(this, items)

        itemsList.adapter = adapter
        itemsList.emptyView = listEmptyMessage
    }

    @Suppress("unused")
    @OnItemClick(R.id.items_list)
    internal fun onItemClick(position: Int) {
        Toast.makeText(this, "You clicked: " + adapter.getItem(position), Toast.LENGTH_SHORT).show()
    }

    @Suppress("unused")
    @OnClick(R.id.add_item)
    internal fun addItem() {
        val name = newItemName.text.toString()

        if (name.isNotEmpty()) {
            newItemName.text.clear()
            items.add(Item(name, System.currentTimeMillis()))
            adapter.notifyDataSetChanged()
        }
    }
}
