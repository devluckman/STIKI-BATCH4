package com.man.filmku.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.man.filmku.databinding.ItemGenreBinding

class GenreAdapter : RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    private val dataList = mutableListOf<String>()

    class ViewHolder(private val binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: String) {
            binding.apply {
                tvGenre.text = data
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGenreBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data)
    }

    fun submit(data : List<String>) {
        dataList.clear()
        dataList.addAll(data)
        notifyItemChanged(0, dataList.size)
    }
}