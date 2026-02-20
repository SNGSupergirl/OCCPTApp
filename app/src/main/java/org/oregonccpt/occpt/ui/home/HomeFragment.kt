package org.oregonccpt.occpt.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
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

        binding.findChildCareButton.setOnClickListener {
            val url = "https://findunionchildcareor.org/find-child-care"
            startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
        }

        binding.memberResourcesButton.setOnClickListener {
            val url = "https://findunionchildcareor.org/resources"
            startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
        }

        binding.becomeUnionProviderButton.setOnClickListener {
            val url = "https://findunionchildcareor.org/join-us"
            startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
        }

        binding.whoWeAreButton.setOnClickListener {
            val url = "https://findunionchildcareor.org/about-us"
            startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
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
