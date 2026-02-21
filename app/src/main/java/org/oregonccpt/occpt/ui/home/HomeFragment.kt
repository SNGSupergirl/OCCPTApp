package org.oregonccpt.occpt.ui.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import org.oregonccpt.occpt.R
import org.oregonccpt.occpt.databinding.FragmentHomeBinding
import java.util.Timer
import java.util.TimerTask

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var slideshowAdapter: SlideshowAdapter
    private val images = listOf(
        R.drawable.home_2019_december_mtg_training,
        R.drawable.home_2020_april_capitol_protest,
        R.drawable.home_2020_april_saturday_kates_house,
        R.drawable.home_2018_retreat_class,
        R.drawable.home_2018_bargaining_team
    )
    private var currentPage = 0
    private var timer: Timer? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        slideshowAdapter = SlideshowAdapter(images)
        binding.viewPager.adapter = slideshowAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { _, _ -> }.attach()

        createSlideShow()

        binding.whoWeAreButton.setOnClickListener {
            findNavController().navigate(R.id.nav_about)
        }

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

        return binding.root
    }

    private fun createSlideShow() {
        val handler = Handler(Looper.getMainLooper())
        val update = Runnable {
            if (currentPage == images.size) {
                currentPage = 0
            }
            binding.viewPager.setCurrentItem(currentPage++, true)
        }

        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, 3000, 3000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        timer?.cancel()
        _binding = null
    }
}