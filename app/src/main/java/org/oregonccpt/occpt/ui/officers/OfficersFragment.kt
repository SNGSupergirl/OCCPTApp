package org.oregonccpt.occpt.ui.officers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.oregonccpt.occpt.R
import org.oregonccpt.occpt.databinding.FragmentOfficersBinding

class OfficersFragment : Fragment() {

    private var _binding: FragmentOfficersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOfficersBinding.inflate(inflater, container, false)

        val officers = listOf(
            Officer("Sabi Velasco", "President and Steward", R.drawable.sabi, "oregonccpt132@gmail.com", "971-998-2730"),
            Officer("Jennifer Gleason", "1st Vice President", R.drawable.jenpic, null, null),
            Officer("Amber Oldridge", "2nd Vice President", R.drawable.amberopic, null, null),
            Officer("Anneliese Sheahan", "Local Treasurer, Chief Union Steward, Local Union Rep and Council Independent Sector VP", R.drawable.anneliesse_photo, "oregonccpt132.stewards@gmail.com", "971-204-9110"),
            Officer("Vanessa Brown", "Local Secretary", R.drawable.vannessa_brown, "littleswanschildcarekf@gmail.com", "541-892-3335"),
            Officer("Norma Mondragon-Hernandez", "NW Regional Director, Washington County", R.drawable.norma_picture, null, null),
            Officer("Mable Bartlett", "NW Regional Director (not Washington county)", R.drawable.occpt_app_icon_5, null, null),
            Officer("Candy Clinkenbeard", "Eastern Regional Director", R.drawable.occpt_app_icon_5, null, null),
            Officer("NC Region Multnomah Co", "Vacant", R.drawable.occpt_app_icon_5, null, null),
            Officer("Zack Wolchesky", "North Central Regional Director (not Multnomah County)", R.drawable.occpt_app_icon_5, "wolchesky@gmail.com", "971-356-5167"),
            Officer("Sheila Ullom", "South Valley Regional Director", R.drawable.occpt_app_icon_5, null, null),
            Officer("Alisha Cox", "Valley Regional Director", R.drawable.alisha_cox, null, null),
            Officer("Jean Bergeron", "Central Regional Director", R.drawable.occpt_app_icon_5, null, null),
            Officer("Rhonda Schock", "Trustee (2022-2025)", R.drawable.occpt_app_icon_5, null, null),
            Officer("Trustee()", "", R.drawable.occpt_app_icon_5, null, null),
            Officer("Staff Position Vacant", "Staff Vacant", R.drawable.occpt_app_icon_5, "oregonccpt132.stewards@gmail.com", null),
            Officer("Staff Rep", "TBA", R.drawable.occpt_app_icon_5, null, null)
        )

        val adapter = OfficersAdapter(officers)
        binding.officersRecyclerView.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}