package org.oregonccpt.occpt.ui.contact

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import org.oregonccpt.occpt.R
import org.oregonccpt.occpt.databinding.FragmentContactBinding

class ContactFragment : Fragment() {

    private var _binding: FragmentContactBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.updateInfoButton.setOnClickListener {
            val url = "https://www.afscme.org/update-my-information"
            startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
        }

        val resourcesText = getString(R.string.contact_resources_text)
        binding.resourcesText.text = HtmlCompat.fromHtml(resourcesText, HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding.resourcesText.movementMethod = LinkMovementMethod.getInstance()

        binding.shareButton.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "https://www.facebook.com/sharer/sharer.php?u=https://findunionchildcareor.org/resources")
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }

        val questionsText = getString(R.string.contact_questions_text)
        binding.questionsText.text = HtmlCompat.fromHtml(questionsText, HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding.questionsText.movementMethod = LinkMovementMethod.getInstance()

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}