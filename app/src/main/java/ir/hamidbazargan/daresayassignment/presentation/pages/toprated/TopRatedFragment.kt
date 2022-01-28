package ir.hamidbazargan.daresayassignment.presentation.pages.toprated

import ir.hamidbazargan.daresayassignment.presentation.movielist.*
import ir.hamidbazargan.daresayassignment.presentation.movielist.MovieListFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class TopRatedFragment : MovieListFragment() {

    override val movieListViewModel by viewModel<MovieListViewModel>(named("topRated"))
}