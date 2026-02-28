package org.oregonccpt.occpt.ui.benefits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
            Benefit("Benefit 1", "Description for benefit 1"),
            Benefit("Benefit 2", "Description for benefit 2"),
            Benefit("Benefit 3", "Description for benefit 3")
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