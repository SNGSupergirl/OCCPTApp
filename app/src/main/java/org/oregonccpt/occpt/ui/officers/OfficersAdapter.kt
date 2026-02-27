package org.oregonccpt.occpt.ui.officers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.oregonccpt.occpt.R
import org.oregonccpt.occpt.databinding.ItemOfficerCardBinding

data class Officer(val name: String, val title: String, val photo: Int, val email: String?, val phone: String?)

class OfficersAdapter(private val officers: List<Officer>) : RecyclerView.Adapter<OfficersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemOfficerCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val officer = officers[position]
        holder.binding.officerName.text = officer.name
        holder.binding.officerTitle.text = officer.title

        if (officer.email != null) {
            holder.binding.officerEmail.text = officer.email
            holder.binding.officerEmail.visibility = View.VISIBLE
        } else {
            holder.binding.officerEmail.visibility = View.GONE
        }

        if (officer.phone != null) {
            holder.binding.officerPhone.text = officer.phone
            holder.binding.officerPhone.visibility = View.VISIBLE
        } else {
            holder.binding.officerPhone.visibility = View.GONE
        }

        if (officer.photo != 0) {
            holder.binding.officerPhoto.load(officer.photo) {
                placeholder(R.drawable.ic_profile_placeholder)
            }
        } else {
            holder.binding.officerPhoto.setImageResource(R.drawable.ic_profile_placeholder)
        }
    }

    override fun getItemCount() = officers.size

    inner class ViewHolder(val binding: ItemOfficerCardBinding) : RecyclerView.ViewHolder(binding.root)
}