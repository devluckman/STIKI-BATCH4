package com.man.filmku.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.man.filmku.databinding.ItemPopularBinding
import com.man.filmku.model.movie.MovieData

class AdapterPopular : RecyclerView.Adapter<AdapterPopular.ViewHolder>() {

    private val dataList = mutableListOf<MovieData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPopularBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = dataList.size // 2

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data)
    }

    fun setData(data : List<MovieData>) {
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }


    class ViewHolder(
        private val binding: ItemPopularBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data : MovieData) {

            binding.tvTitle.text = data.title
            binding.tvRating.text = data.rating

        }

    }

}