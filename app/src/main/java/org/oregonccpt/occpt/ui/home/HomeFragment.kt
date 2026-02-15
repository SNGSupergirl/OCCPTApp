package org.oregonccpt.occpt.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import org.oregonccpt.occpt.R
import org.oregonccpt.occpt.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val images = listOf(
            R.drawable.home_2019_class,
            R.drawable.home_2019_retreat,
            R.drawable.home_training_2020,
            R.drawable.home_2018_retreat_class,
            R.drawable.home_2018_bargaining_team,
            R.drawable.home_2020_protest_james_hunter,
            R.drawable.home_board_with_e_mcbride_2019,
            R.drawable.home_2019_december_mtg_training,
            R.drawable.home_2020_april_capitol_protest,
            R.drawable.home_2020_april_saturday_kates_house
        )

        for (imageResId in images) {
            val imageView = ImageView(requireContext()).apply {
                scaleType = ImageView.ScaleType.CENTER_CROP
                setImageResource(imageResId)
            }
            binding.slideshowFlipper.addView(imageView)
        }

        binding.slideshowFlipper.flipInterval = 3000 // 3 seconds
        binding.slideshowFlipper.isAutoStart = true

        binding.signupButton.setOnClickListener {
            val url = "https://findunionchildcareor.org/sign-up-page?"
            startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
