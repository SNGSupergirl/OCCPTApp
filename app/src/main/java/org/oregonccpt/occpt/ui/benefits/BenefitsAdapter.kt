package org.oregonccpt.occpt.ui.benefits

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.oregonccpt.occpt.databinding.ItemBenefitCardBinding

data class Benefit(val title: String, val description: String, var isExpanded: Boolean = false)

class BenefitsAdapter(private val benefits: List<Benefit>) : RecyclerView.Adapter<BenefitsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBenefitCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val benefit = benefits[position]
        holder.binding.benefitTitle.text = benefit.title
        holder.binding.benefitDescription.text = benefit.description

        holder.binding.benefitDescription.visibility = if (benefit.isExpanded) View.VISIBLE else View.GONE

        holder.itemView.setOnClickListener {
            benefit.isExpanded = !benefit.isExpanded
            notifyItemChanged(position)
        }
    }

    override fun getItemCount() = benefits.size

    inner class ViewHolder(val binding: ItemBenefitCardBinding) : RecyclerView.ViewHolder(binding.root)
}