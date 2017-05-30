package io.github.kafumi.viewbindingsamples

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val items = ArrayList<Item>()
    private lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ListAdapter(this, items)

        items_list.adapter = adapter
        items_list.emptyView = list_empty_message

        setupClickListeners()
    }

    private fun setupClickListeners() {
        items_list.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, "You clicked: " + adapter.getItem(position), Toast.LENGTH_SHORT).show()
        }

        add_item.setOnClickListener {
            val name = new_item_name.text.toString()

            if (name.isNotEmpty()) {
                new_item_name.text.clear()
                items.add(Item(name, System.currentTimeMillis()))
                adapter.notifyDataSetChanged()
            }
        }
    }
}
