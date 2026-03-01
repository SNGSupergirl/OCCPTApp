package org.oregonccpt.occpt.ui.benefits

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import org.oregonccpt.occpt.databinding.ItemBenefitCardBinding

data class Benefit(
    val title: String? = null,
    val description: String,
    val titleImage: Int? = null,
    val moreInfoUrl: String? = null,
    val faqUrl: String? = null,
    var isExpanded: Boolean = false
)

class BenefitsAdapter(private val benefits: List<Benefit>) : RecyclerView.Adapter<BenefitsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBenefitCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val benefit = benefits[position]

        if (benefit.title != null) {
            holder.binding.benefitTitle.text = benefit.title
            holder.binding.benefitTitle.visibility = View.VISIBLE
            holder.binding.benefitTitleImage.visibility = View.GONE
        } else if (benefit.titleImage != null) {
            holder.binding.benefitTitleImage.setImageResource(benefit.titleImage)
            holder.binding.benefitTitleImage.visibility = View.VISIBLE
            holder.binding.benefitTitle.visibility = View.GONE
        }

        holder.binding.benefitDescription.text = benefit.description

        if (benefit.isExpanded) {
            holder.binding.benefitDetailsLayout.visibility = View.VISIBLE
            if (benefit.moreInfoUrl != null) {
                holder.binding.moreInfoButton.visibility = View.VISIBLE
                holder.binding.moreInfoButton.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse(benefit.moreInfoUrl)
                    }
                    ContextCompat.startActivity(holder.itemView.context, intent, null)
                }
            } else {
                holder.binding.moreInfoButton.visibility = View.GONE
            }

            if (benefit.faqUrl != null) {
                holder.binding.faqButton.visibility = View.VISIBLE
                holder.binding.faqButton.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse(benefit.faqUrl)
                    }
                    ContextCompat.startActivity(holder.itemView.context, intent, null)
                }
            } else {
                holder.binding.faqButton.visibility = View.GONE
            }
        } else {
            holder.binding.benefitDetailsLayout.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            benefit.isExpanded = !benefit.isExpanded
            notifyItemChanged(position)
        }
    }

    override fun getItemCount() = benefits.size

    inner class ViewHolder(val binding: ItemBenefitCardBinding) : RecyclerView.ViewHolder(binding.root)
}