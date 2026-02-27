package org.oregonccpt.occpt.ui.officers

import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.oregonccpt.occpt.R
import org.oregonccpt.occpt.databinding.ItemOfficerCardBinding

data class Officer(val name: String, val title: String, val photo: Int, val email: String?, val phone: String?, val bio: String?, var isExpanded: Boolean = false)

class OfficersAdapter(private val officers: List<Officer>) : RecyclerView.Adapter<OfficersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemOfficerCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val officer = officers[position]
        holder.binding.officerName.text = officer.name
        holder.binding.officerTitle.text = officer.title
        holder.binding.officerBio.text = officer.bio

        holder.binding.officerBio.visibility = if (officer.isExpanded) View.VISIBLE else View.GONE

        if (officer.email != null) {
            holder.binding.officerEmail.text = officer.email
            holder.binding.officerEmail.visibility = View.VISIBLE
            holder.binding.emailIcon.visibility = View.VISIBLE
            holder.binding.emailIcon.setOnClickListener {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:${officer.email}")
                }
                ContextCompat.startActivity(holder.itemView.context, intent, null)
            }
        } else {
            holder.binding.officerEmail.visibility = View.GONE
            holder.binding.emailIcon.visibility = View.GONE
        }

        if (officer.phone != null) {
            holder.binding.officerPhone.text = officer.phone
            holder.binding.officerPhone.visibility = View.VISIBLE
            holder.binding.callIcon.visibility = View.VISIBLE
            holder.binding.callIcon.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:${officer.phone}")
                }
                ContextCompat.startActivity(holder.itemView.context, intent, null)
            }
        } else {
            holder.binding.officerPhone.visibility = View.GONE
            holder.binding.callIcon.visibility = View.GONE
        }

        if (officer.photo != 0) {
            holder.binding.officerPhoto.load(officer.photo) {
                placeholder(R.drawable.ic_profile_placeholder)
            }
        } else {
            holder.binding.officerPhoto.setImageResource(R.drawable.ic_profile_placeholder)
        }

        if(officer.email != null || officer.phone != null) {
            holder.binding.addContactIcon.visibility = View.VISIBLE
            holder.binding.addContactIcon.setOnClickListener {
                val intent = Intent(ContactsContract.Intents.Insert.ACTION).apply {
                    type = ContactsContract.RawContacts.CONTENT_TYPE
                    putExtra(ContactsContract.Intents.Insert.NAME, officer.name)
                    putExtra(ContactsContract.Intents.Insert.EMAIL, officer.email)
                    putExtra(ContactsContract.Intents.Insert.PHONE, officer.phone)
                }
                ContextCompat.startActivity(holder.itemView.context, intent, null)
            }
        } else {
            holder.binding.addContactIcon.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            officer.isExpanded = !officer.isExpanded
            notifyItemChanged(position)
        }
    }

    override fun getItemCount() = officers.size

    inner class ViewHolder(val binding: ItemOfficerCardBinding) : RecyclerView.ViewHolder(binding.root)
}