package org.oregonccpt.occpt.ui.joinafscme

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import org.oregonccpt.occpt.databinding.FragmentJoinAfscmeBinding

class JoinAFSCMEFragment : Fragment() {

    private var _binding: FragmentJoinAfscmeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJoinAfscmeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val browserIntent = Intent(Intent.ACTION_VIEW, "https://findunionchildcareor.org/join-our-union".toUri())
        binding.joinButton1.setOnClickListener {
            startActivity(browserIntent)
        }
        binding.joinButton2.setOnClickListener {
            startActivity(browserIntent)
        }
        binding.privacyPolicyLink.setOnClickListener {
            val url = "https://findunionchildcareor.org/privacy-policy"
            startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
