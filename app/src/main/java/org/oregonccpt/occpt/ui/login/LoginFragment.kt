package org.oregonccpt.occpt.ui.login

import android.os.Bundle
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
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

        binding.loginButton.setOnClickListener {
            Toast.makeText(requireContext(), "Login clicked", Toast.LENGTH_SHORT).show()
        }
        binding.facebookIcon.setOnClickListener {
            val url = "https://www.facebook.com/AFSCMEChildCareProviders/"
            startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
        }

        binding.twitterIcon.setOnClickListener {
            val url = "https://twitter.com/AFSCME"
            startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}