package com.man.filmku.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.man.filmku.databinding.ItemNowPlayingBinding
import com.man.filmku.domain.model.movie.MovieData

class AdapterNowShowing : RecyclerView.Adapter<AdapterNowShowing.ViewHolder>() {

    private val dataList = mutableListOf<MovieData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNowPlayingBinding.inflate(
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
        private val binding: ItemNowPlayingBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data : MovieData) {

            binding.tvTitle.text = data.title
            binding.tvRating.text = data.rating

        }

    }

}