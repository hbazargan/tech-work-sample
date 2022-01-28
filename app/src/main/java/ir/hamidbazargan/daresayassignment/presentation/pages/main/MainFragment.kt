package ir.hamidbazargan.daresayassignment.presentation.pages.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import ir.hamidbazargan.daresayassignment.R
import ir.hamidbazargan.daresayassignment.databinding.FragmentMainBinding
import ir.hamidbazargan.daresayassignment.presentation.pages.main.PagesAdapter

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        binding.pager.adapter = PagesAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            when(position) {
                0 -> {
                    tab.text = getString(R.string.popular_movies)
                }
                1 -> {
                    tab.text = getString(R.string.top_rated_movies)
                }
                2 -> {
                    tab.text = getString(R.string.bookmark_movies)
                }
            }
        }.attach()
    }

    private fun initToolbar() {
        binding.toolbar.title = getString(R.string.app_name)
    }
}