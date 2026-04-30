package org.pillarsoforegon.pillarsapp.ui.joinafscme

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import org.pillarsoforegon.pillarsapp.R
import org.pillarsoforegon.pillarsapp.databinding.FragmentJoinPillarsBinding

class JoinPILLARSFragment : Fragment() {

    private var _binding: FragmentJoinPillarsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJoinPillarsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val browserIntent = Intent(Intent.ACTION_VIEW, "https://www.pillarsoforegon.org/become-a-member".toUri())

        binding.joinButton2.setOnClickListener {
            startActivity(browserIntent)
        }

        val membersGetBodyText = getString(R.string.join_afscme_members_get_body)
        binding.membersGetBody.text = HtmlCompat.fromHtml(membersGetBodyText, HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding.membersGetBody.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
