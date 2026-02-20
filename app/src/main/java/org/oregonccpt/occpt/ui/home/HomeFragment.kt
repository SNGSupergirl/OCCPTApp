package org.oregonccpt.occpt.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.oregonccpt.occpt.R
import org.oregonccpt.occpt.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.parentCard.setOnClickListener {
            val url = "https://findunionchildcareor.org/find-child-care"
            startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
        }

        binding.providerCard.setOnClickListener {
            val url = "https://findunionchildcareor.org/resources"
            startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
        }

        binding.joinCard.setOnClickListener {
            findNavController().navigate(R.id.nav_join_afscme)
        }

        binding.whoWeAreButton.setOnClickListener {
            findNavController().navigate(R.id.nav_about)
        }

        binding.privacyPolicyLink.setOnClickListener {
            val url = "https://findunionchildcareor.org/privacy-policy"
            startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
        }

        binding.facebookIcon.setOnClickListener {
            val url = "https://www.facebook.com/AFSCMEChildCareProviders/"
            startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
        }

        binding.twitterIcon.setOnClickListener {
            val url = "https://twitter.com/AFSCME"
            startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
