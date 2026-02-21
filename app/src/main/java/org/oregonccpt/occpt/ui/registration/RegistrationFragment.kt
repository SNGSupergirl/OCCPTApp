package org.oregonccpt.occpt.ui.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.oregonccpt.occpt.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Handle the register button click
        binding.registerButton.setOnClickListener {
            // TODO: Add your registration logic here (e.g., validate inputs, call API)
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()


            if (email.isNotEmpty()) {
                Toast.makeText(requireContext(), "Registration submitted for $email", Toast.LENGTH_SHORT).show()
                // Optionally, navigate back to log in after successful registration
                findNavController().navigateUp()
            } else {
                Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show()
            }
        }

        // Handle click to go back to the login screen
        binding.goToLoginLink.setOnClickListener {
            // Navigate up (back) in the navigation graph
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}