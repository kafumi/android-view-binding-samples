package io.github.kafumi.viewbindingsamples

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.*
import kotterknife.bindView

class MainActivity : AppCompatActivity() {
    private val itemsList: ListView by bindView(R.id.items_list)
    private val listEmptyMessage: TextView by bindView(R.id.list_empty_message)
    private val newItemName: EditText by bindView(R.id.new_item_name)
    private val addItem: Button by bindView(R.id.add_item)

    private val items = ArrayList<Item>()
    private lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ListAdapter(this, items)

        itemsList.adapter = adapter
        itemsList.emptyView = listEmptyMessage

        setupClickListeners()
    }

    private fun setupClickListeners() {
        itemsList.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, "You clicked: " + adapter.getItem(position), Toast.LENGTH_SHORT).show()
        }

        addItem.setOnClickListener {
            val name = newItemName.text.toString()

            if (name.isNotEmpty()) {
                newItemName.text.clear()
                items.add(Item(name, System.currentTimeMillis()))
                adapter.notifyDataSetChanged()
            }
        }
    }
}
