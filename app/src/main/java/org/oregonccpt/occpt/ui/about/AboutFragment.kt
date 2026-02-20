package org.oregonccpt.occpt.ui.about

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import org.oregonccpt.occpt.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)

        binding.link1.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, "https://www.oregonccpt.org/docs/2024%20September%20to%20AFSCME%20Charges%20against%20Council%2075%20and%20individuals.pdf".toUri()))
        }

        binding.link2.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, "https://www.oregonccpt.org/docs/2024%20Charges%20to%20Council%20Secretary%20Sheahan%20and%20Local%20132%20Exhibit%201.pdf".toUri()))
        }

        binding.link3.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, "https://www.oregonccpt.org/docs/2024%20Charges%20to%20Council%20Secretary%20Sheahan%20and%20Local%20132%20Exhibits%202-21.pdf".toUri()))
        }

        binding.link4.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, "https://www.oregonccpt.org/docs/Screenshot_20250502_205015_Gmail.jpg".toUri()))
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
