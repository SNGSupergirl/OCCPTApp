package org.oregonccpt.occpt.ui.benefits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.oregonccpt.occpt.R
import org.oregonccpt.occpt.databinding.FragmentBenefitsBinding

class BenefitsFragment : Fragment() {

    private var _binding: FragmentBenefitsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBenefitsBinding.inflate(inflater, container, false)

        val benefits = listOf(
            Benefit("State & CCPT Childcare Retirement Benefit", "The State & CCPT Child Care Retirement Benefit is retirement account funding paid by the State of Oregon to all registered and certified family child care providers (RF/CF providers) who have an active license on of 12/11/23."),
            Benefit(titleImage = R.drawable.healthiestyou_logo, description = "Connect to doctors 24/7 for diagnosis, treatment, and more, all from a single app as part of your HealthiestYou membership. A free, fast, and easy way to take care of your health."),
            Benefit("Dental", "More information coming soon!")
        )

        val adapter = BenefitsAdapter(benefits)
        binding.benefitsRecyclerView.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}