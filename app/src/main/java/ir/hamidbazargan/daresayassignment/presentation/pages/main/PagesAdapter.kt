package ir.hamidbazargan.daresayassignment.presentation.pages.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ir.hamidbazargan.daresayassignment.presentation.pages.popular.PopularFragment
import ir.hamidbazargan.daresayassignment.presentation.pages.bookmark.BookmarkFragment
import ir.hamidbazargan.daresayassignment.presentation.pages.toprated.TopRatedFragment

class PagesAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> PopularFragment()
            1 -> TopRatedFragment()
            else -> BookmarkFragment()
        }
    }
}