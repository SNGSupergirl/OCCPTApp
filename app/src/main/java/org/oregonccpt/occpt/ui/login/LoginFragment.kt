package org.oregonccpt.occpt.ui.login

import android.os.Bundle
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.oregonccpt.occpt.R
import org.oregonccpt.occpt.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        binding.loginButton.setOnClickListener {
            Toast.makeText(requireContext(), "Login clicked", Toast.LENGTH_SHORT).show()
        }

        binding.privacyPolicyLink.setOnClickListener {
            openUrl("https://findunionchildcareor.org/privacy-policy")
        }
        binding.facebookIcon.setOnClickListener {
            openUrl("https://www.facebook.com/AFSCMEChildCareProviders/")
        }

        binding.twitterIcon.setOnClickListener {
            openUrl("https://twitter.com/AFSCME")
        }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        val packageManager = requireContext().packageManager
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(requireContext(), "No application available to open this link.", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}