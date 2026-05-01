package org.pillarsoforegon.pillarsapp.ui.registration

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
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import org.pillarsoforegon.pillarsapp.R
import org.pillarsoforegon.pillarsapp.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

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

        setupFirebase()

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

        binding.avatarLayout.setOnClickListener {
            showEditAvatarOrBorderDialog()
        }

        // Handle the register button click
        binding.registerButton.setOnClickListener {
            registerUser()
        }

        // Handle click to go back to the login screen
        binding.goToLoginLink.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupFirebase() {
        try {
            if (FirebaseApp.getApps(requireContext()).isEmpty()) {
                val options = FirebaseOptions.Builder()
                    .setApiKey("AIzaSyAOotggMrhBYv9l8lyP0IRhjTbsz-oNpf0")
                    .setApplicationId("1:231241868866:web:3be2ccd0f4c6ac16faafdb")
                    .setDatabaseUrl("https://pillars-a3fff-default-rtdb.firebaseio.com")
                    .setProjectId("pillars-a3fff")
                    .setStorageBucket("pillars-a3fff.firebasestorage.app")
                    .build()
                FirebaseApp.initializeApp(requireContext(), options)
            }
            auth = FirebaseAuth.getInstance()
            database = FirebaseDatabase.getInstance()
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Firebase init error: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun registerUser() {
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
        val pillarsMember = if (binding.pillarsMemberCheckbox.isChecked) "Yes" else "No"
        val parent = if (binding.parentCheckbox.isChecked) "Yes" else "No"
        val registeredProvider = if (binding.registeredProviderCheckbox.isChecked) "Yes" else "No"
        val certifiedProvider = if (binding.certifiedProviderCheckbox.isChecked) "Yes" else "No"
        val certifiedCenter = if (binding.certifiedCenterCheckbox.isChecked) "Yes" else "No"
        val unlicensedProvider = if (binding.unlicensedProviderCheckbox.isChecked) "Yes" else "No"
        val username = binding.usernameEditText.text.toString().trim()
        val password = binding.passwordEditText.text.toString()

        val isAnyFieldEmpty = firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || city.isEmpty() || postalCode.isEmpty() || county.isEmpty() || country.isEmpty() ||
                phone.isEmpty() || email.isEmpty() || confirmEmail.isEmpty() || username.isEmpty() || password.isEmpty()

        if (binding.phoneProviderDropdown.selectedItemPosition == 0) {
            Toast.makeText(requireContext(), "Please select your cell phone carrier", Toast.LENGTH_SHORT).show()
            return
        }
        if (isAnyFieldEmpty) {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show()
            return
        }
        if (email != confirmEmail) {
            Toast.makeText(requireContext(), "Emails do not match", Toast.LENGTH_SHORT).show()
            return
        }

        binding.registerButton.isEnabled = false

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid
                    val user = User(
                        firstName, lastName, address, city, state, postalCode, county, country,
                        phone, phoneProvider, textAlerts, email, emailView, emailList,
                        pillarsMember, parent, registeredProvider, certifiedProvider,
                        certifiedCenter, unlicensedProvider, username
                    )

                    if (userId != null) {
                        database.getReference("users").child(userId).setValue(user)
                            .addOnSuccessListener {
                                Toast.makeText(requireContext(), "Registration successful!", Toast.LENGTH_SHORT).show()
                                findNavController().navigateUp()
                            }
                            .addOnFailureListener { e ->
                                binding.registerButton.isEnabled = true
                                Toast.makeText(requireContext(), "Database error: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                    }
                } else {
                    binding.registerButton.isEnabled = true
                    Toast.makeText(requireContext(), "Auth error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun showEditAvatarOrBorderDialog() {
        val options = arrayOf("Change Avatar", "Change Border")
        AlertDialog.Builder(requireContext())
            .setTitle("Customize your profile")
            .setItems(options) { dialog, which ->
                when (which) {
                    0 -> showAvatarPickerDialog()
                    1 -> showBorderPickerDialog()
                }
            }
            .show()
    }

    private fun showAvatarPickerDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_avatar_picker, null)
        builder.setView(dialogView)

        val dialog = builder.create()

        dialogView.findViewById<ImageView>(R.id.avatar_1).setOnClickListener {
            binding.avatarImageView.setImageResource(R.drawable.ic_avatar_1)
            dialog.dismiss()
        }
        dialogView.findViewById<ImageView>(R.id.avatar_2).setOnClickListener {
            binding.avatarImageView.setImageResource(R.drawable.ic_avatar_2)
            dialog.dismiss()
        }
        dialogView.findViewById<ImageView>(R.id.avatar_3).setOnClickListener {
            binding.avatarImageView.setImageResource(R.drawable.ic_avatar_3)
            dialog.dismiss()
        }
        dialogView.findViewById<ImageView>(R.id.avatar_4).setOnClickListener {
            binding.avatarImageView.setImageResource(R.drawable.ic_avatar_4)
            dialog.dismiss()
        }
        dialogView.findViewById<ImageView>(R.id.avatar_5).setOnClickListener {
            binding.avatarImageView.setImageResource(R.drawable.ic_avatar_5)
            dialog.dismiss()
        }
        dialogView.findViewById<ImageView>(R.id.avatar_6).setOnClickListener {
            binding.avatarImageView.setImageResource(R.drawable.ic_avatar_6)
            dialog.dismiss()
        }
        dialogView.findViewById<ImageView>(R.id.avatar_7).setOnClickListener {
            binding.avatarImageView.setImageResource(R.drawable.ic_avatar_7)
            dialog.dismiss()
        }
        dialogView.findViewById<ImageView>(R.id.avatar_8).setOnClickListener {
            binding.avatarImageView.setImageResource(R.drawable.ic_avatar_8)
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showBorderPickerDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_border_picker, null)
        builder.setView(dialogView)

        val dialog = builder.create()

        dialogView.findViewById<ImageView>(R.id.border_1).setOnClickListener {
            binding.avatarBorderImageView.setImageResource(R.drawable.user_boarder_1)
            dialog.dismiss()
        }
        dialogView.findViewById<ImageView>(R.id.border_2).setOnClickListener {
            binding.avatarBorderImageView.setImageResource(R.drawable.user_boarder_2)
            dialog.dismiss()
        }
        dialogView.findViewById<ImageView>(R.id.border_3).setOnClickListener {
            binding.avatarBorderImageView.setImageResource(R.drawable.user_boarder_3)
            dialog.dismiss()
        }
        dialogView.findViewById<ImageView>(R.id.border_4).setOnClickListener {
            binding.avatarBorderImageView.setImageResource(R.drawable.user_boarder_4)
            dialog.dismiss()
        }
        dialogView.findViewById<ImageView>(R.id.border_5).setOnClickListener {
            binding.avatarBorderImageView.setImageResource(R.drawable.user_boarder_5)
            dialog.dismiss()
        }
        dialogView.findViewById<ImageView>(R.id.border_6).setOnClickListener {
            binding.avatarBorderImageView.setImageResource(R.drawable.user_boarder_6)
            dialog.dismiss()
        }
        dialogView.findViewById<ImageView>(R.id.border_7).setOnClickListener {
            binding.avatarBorderImageView.setImageResource(R.drawable.user_boarder_7)
            dialog.dismiss()
        }
        dialogView.findViewById<ImageView>(R.id.border_8).setOnClickListener {
            binding.avatarBorderImageView.setImageResource(R.drawable.user_boarder_8)
            dialog.dismiss()
        }
        dialogView.findViewById<ImageView>(R.id.border_9).setOnClickListener {
            binding.avatarBorderImageView.setImageResource(R.drawable.user_boarder_9)
            dialog.dismiss()
        }
        dialogView.findViewById<ImageView>(R.id.border_10).setOnClickListener {
            binding.avatarBorderImageView.setImageResource(R.drawable.user_boarder_10)
            dialog.dismiss()
        }
        dialogView.findViewById<ImageView>(R.id.border_11).setOnClickListener {
            binding.avatarBorderImageView.setImageResource(R.drawable.user_boarder_11)
            dialog.dismiss()
        }
        dialogView.findViewById<ImageView>(R.id.border_12).setOnClickListener {
            binding.avatarBorderImageView.setImageResource(R.drawable.user_boarder_12)
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
