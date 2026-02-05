package com.easyo.recyclermemoryleaksample.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.easyo.recyclermemoryleaksample.databinding.ItemSampleBinding

class BadRecyclerAdapter(
    private val onItemClicked: (Int) -> Unit
) : ListAdapter<String, BadViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BadViewHolder {
        val binding = ItemSampleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BadViewHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: BadViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }
}

class BadViewHolder(
    private val binding: ItemSampleBinding,
    private val onItemClicked: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(text: String, pos: Int) {
        binding.textView.text = text

        // set lambda for every new viewHolder
        binding.root.setOnClickListener {
            onItemClicked(pos)
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(old: String, new: String) = old == new
    override fun areContentsTheSame(old: String, new: String) = old == new
}