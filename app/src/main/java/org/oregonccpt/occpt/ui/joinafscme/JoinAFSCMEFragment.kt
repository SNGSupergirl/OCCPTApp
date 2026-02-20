package org.oregonccpt.occpt.ui.joinafscme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
