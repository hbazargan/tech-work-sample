package ir.hamidbazargan.daresayassignment.presentation.pages.bookmark

import ir.hamidbazargan.daresayassignment.presentation.movielist.*
import ir.hamidbazargan.daresayassignment.presentation.movielist.MovieListFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class BookmarkFragment : MovieListFragment() {

    override val movieListViewModel by viewModel<MovieListViewModel>(named("bookmark"))
}