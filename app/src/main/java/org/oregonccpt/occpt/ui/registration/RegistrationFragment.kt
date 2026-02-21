package org.oregonccpt.occpt.ui.registration

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.oregonccpt.occpt.R
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

        // Populate the state dropdown
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.states_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.stateDropdown.adapter = adapter
            val oregonPosition = adapter.getPosition("Oregon")
            if (oregonPosition >= 0) {
                binding.stateDropdown.setSelection(oregonPosition)
            }
        }

        // Populate the phone provider dropdown
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.carriers_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.phoneProviderDropdown.adapter = adapter
        }

      //  binding.countryEditText.setText(getString(R.string.registration_country_default))

        binding.avatarImageView.setOnClickListener {
            showAvatarPickerDialog()
        }

        // Handle the register button click
        binding.registerButton.setOnClickListener {
            val firstName = binding.firstNameEditText.text.toString().trim()
            val lastName = binding.lastNameEditText.text.toString().trim()
            val address = binding.addressEditText.text.toString().trim()
            val city = binding.cityEditText.text.toString().trim()
            val state = binding.stateDropdown.selectedItem.toString()
            val postalCode = binding.postalCodeEditText.text.toString().trim()
            val county = binding.countyEditText.text.toString().trim()
            val country = binding.countryEditText.text.toString().trim()
            val phone = binding.phoneEditText.text.toString().trim()
            val phoneProvider = binding.phoneProviderDropdown.selectedItem.toString()
            val textAlerts = if (binding.textAlertsCheckbox.isChecked) "Yes" else "No"
            val email = binding.emailEditText.text.toString().trim()
            val confirmEmail = binding.confirmEmailEditText.text.toString().trim()
            val emailView = if (binding.emailViewCheckbox.isChecked) "Yes" else "No"
            val emailList = if (binding.emailListCheckbox.isChecked) "Yes" else "No"
            val unionMember = if (binding.unionMemberCheckbox.isChecked) "Yes" else "No"
            val parent = if (binding.parentCheckbox.isChecked) "Yes" else "No"
            val registeredProvider = if (binding.registeredProviderCheckbox.isChecked) "Yes" else "No"
            val certifiedProvider = if (binding.certifiedProviderCheckbox.isChecked) "Yes" else "No"
            val certifiedCenter = if (binding.certifiedCenterCheckbox.isChecked) "Yes" else "No"
            val unlicensedProvider = if (binding.unlicensedProviderCheckbox.isChecked) "Yes" else "No"
            val username = binding.usernameEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString()

            val isAnyFieldEmpty = firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || city.isEmpty() || postalCode.isEmpty() || county.isEmpty() || country.isEmpty() ||
                phone.isEmpty() || phoneProvider.isEmpty() || email.isEmpty() || confirmEmail.isEmpty() || username.isEmpty() || password.isEmpty()

            val isRoleSelected = binding.unionMemberCheckbox.isChecked ||
                    binding.parentCheckbox.isChecked ||
                    binding.registeredProviderCheckbox.isChecked ||
                    binding.certifiedProviderCheckbox.isChecked ||
                    binding.certifiedCenterCheckbox.isChecked ||
                    binding.unlicensedProviderCheckbox.isChecked

            if (binding.phoneProviderDropdown.selectedItemPosition == 0) {
                Toast.makeText(requireContext(), "Please select your cell phone carrier", Toast.LENGTH_SHORT).show()
            } else if (isAnyFieldEmpty) {
                Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show()
            } else if (email != confirmEmail) {
                Toast.makeText(requireContext(), "Emails do not match", Toast.LENGTH_SHORT).show()
            } else if (!isRoleSelected) {
                Toast.makeText(requireContext(), "Please select at least one role (parent, provider, union member)", Toast.LENGTH_SHORT).show()
            } else {
                // Perform registration logic here (e.g. call API)
                Toast.makeText(requireContext(), "Registration submitted for $firstName", Toast.LENGTH_SHORT).show()
                // Optionally, navigate back to log in after successful registration
                findNavController().navigateUp()
            }
        }

        // Handle click to go back to the login screen
        binding.goToLoginLink.setOnClickListener {
            // Navigate up (back) in the navigation graph
            findNavController().navigateUp()
        }
    }

    private fun showAvatarPickerDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_avatar_picker, null)
        builder.setView(dialogView)

        val dialog = builder.create()

        val avatar1 = dialogView.findViewById<ImageView>(R.id.avatar_1)
        val avatar2 = dialogView.findViewById<ImageView>(R.id.avatar_2)
        val avatar3 = dialogView.findViewById<ImageView>(R.id.avatar_3)
        val avatar4 = dialogView.findViewById<ImageView>(R.id.avatar_4)
        val avatar5 = dialogView.findViewById<ImageView>(R.id.avatar_5)
        val avatar6 = dialogView.findViewById<ImageView>(R.id.avatar_6)
        val avatar7 = dialogView.findViewById<ImageView>(R.id.avatar_7)
        val avatar8 = dialogView.findViewById<ImageView>(R.id.avatar_8)

        avatar1.setOnClickListener { 
            binding.avatarImageView.setImageResource(R.drawable.ic_avatar_1)
            dialog.dismiss()
        }
        avatar2.setOnClickListener { 
            binding.avatarImageView.setImageResource(R.drawable.ic_avatar_2)
            dialog.dismiss()
        }
        avatar3.setOnClickListener { 
            binding.avatarImageView.setImageResource(R.drawable.ic_avatar_3)
            dialog.dismiss()
        }
        avatar4.setOnClickListener { 
            binding.avatarImageView.setImageResource(R.drawable.ic_avatar_4)
            dialog.dismiss()
        }
        avatar5.setOnClickListener { 
            binding.avatarImageView.setImageResource(R.drawable.ic_avatar_5)
            dialog.dismiss()
        }
        avatar6.setOnClickListener { 
            binding.avatarImageView.setImageResource(R.drawable.ic_avatar_6)
            dialog.dismiss()
        }
        avatar7.setOnClickListener { 
            binding.avatarImageView.setImageResource(R.drawable.ic_avatar_7)
            dialog.dismiss()
        }
        avatar8.setOnClickListener { 
            binding.avatarImageView.setImageResource(R.drawable.ic_avatar_8)
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}