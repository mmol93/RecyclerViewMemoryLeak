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
        holder.bind(getItem(position))
    }
}

class BadViewHolder(
    private val binding: ItemSampleBinding,
    private val onItemClicked: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    init {
        // Lambdaを最初一回のみセットするよう変更
        binding.root.setOnClickListener {
            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                onItemClicked(bindingAdapterPosition)
            }
        }
    }

    fun bind(text: String) {
        binding.textView.text = text
    }
}

class DiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(old: String, new: String) = old == new
    override fun areContentsTheSame(old: String, new: String) = old == new
}