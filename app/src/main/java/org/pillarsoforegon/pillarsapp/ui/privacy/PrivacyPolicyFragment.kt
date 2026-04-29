package org.pillarsoforegon.pillarsapp.ui.privacy

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.pillarsoforegon.pillarsapp.R
import org.pillarsoforegon.pillarsapp.databinding.FragmentPrivacyPolicyBinding

class PrivacyPolicyFragment : Fragment() {

    private var _binding: FragmentPrivacyPolicyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPrivacyPolicyBinding.inflate(inflater, container, false)
        
        val isSpanish = arguments?.getBoolean("isSpanish") ?: false
        
        if (isSpanish) {
            binding.privacyPolicyTitle.setText(R.string.privacy_policy_spanish_title)
            binding.privacyPolicyContent.text = Html.fromHtml(getString(R.string.privacy_policy_spanish_text), Html.FROM_HTML_MODE_COMPACT)
        } else {
            binding.privacyPolicyTitle.setText(R.string.privacy_policy_title)
            binding.privacyPolicyContent.text = Html.fromHtml(getString(R.string.privacy_policy_text), Html.FROM_HTML_MODE_COMPACT)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}