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
import androidx.core.view.isGone

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.facebookIcon.setOnClickListener {
            val url = "https://www.facebook.com/PILLARSofOregon/"
            startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
        }

        binding.officersButton.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_nav_officers)
        }

        binding.joinUnionButton.setOnClickListener {
            val url = "https://findunionchildcareor.org/sign-up-page/"
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = url.toUri()
            }
            startActivity(intent)
        }

        binding.ourUnionContainer.setOnClickListener {
            if (binding.ourUnionExpandableLayout.isGone) {
                binding.ourUnionExpandableLayout.visibility = View.VISIBLE
                binding.clickMoreInfoLayout.visibility = View.INVISIBLE
            } else {
                binding.ourUnionExpandableLayout.visibility = View.GONE
                binding.clickMoreInfoLayout.visibility = View.VISIBLE
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}