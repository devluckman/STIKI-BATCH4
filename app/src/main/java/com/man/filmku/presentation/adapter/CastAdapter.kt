package com.man.filmku.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.man.filmku.databinding.ItemCastBinding
import com.man.filmku.domain.model.movie.CastMovieData

class CastAdapter : RecyclerView.Adapter<CastAdapter.ViewHolder>() {

    private val dataList = mutableListOf<CastMovieData>()

    class ViewHolder(private val binding: ItemCastBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CastMovieData) {
            binding.apply {
                tvMovieName.text = data.name
                Glide.with(ivPictureCast)
                    .load(data.picture)
                    .transform(CenterCrop(), RoundedCorners(24))
                    .into(ivPictureCast)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCastBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data)
    }

    fun submit(data : List<CastMovieData>) {
        dataList.clear()
        dataList.addAll(data)
        notifyItemChanged(0, dataList.size)
    }
}