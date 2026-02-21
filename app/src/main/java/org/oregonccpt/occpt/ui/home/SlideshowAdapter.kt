package org.oregonccpt.occpt.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.oregonccpt.occpt.databinding.ItemSlideshowBinding

class SlideshowAdapter(private val images: List<Int>) : RecyclerView.Adapter<SlideshowAdapter.SlideshowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlideshowViewHolder {
        val binding = ItemSlideshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SlideshowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SlideshowViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount() = images.size

    inner class SlideshowViewHolder(private val binding: ItemSlideshowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(imageRes: Int) {
            binding.imageView.setImageResource(imageRes)
        }
    }
}