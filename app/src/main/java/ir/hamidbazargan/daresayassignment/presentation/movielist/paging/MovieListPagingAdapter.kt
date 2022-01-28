package ir.hamidbazargan.daresayassignment.presentation.movielist.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import ir.hamidbazargan.daresayassignment.databinding.ItemPopularMovieBinding
import ir.hamidbazargan.daresayassignment.domain.entity.MovieEntity
import ir.hamidbazargan.daresayassignment.presentation.movielist.ClickListener
import ir.hamidbazargan.daresayassignment.presentation.movielist.MovieViewHolder

class MovieListPagingAdapter(
    diffCallback: DiffUtil.ItemCallback<MovieEntity>,
    private val clickListener: ClickListener
) : PagingDataAdapter<MovieEntity, MovieViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemPopularMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            clickListener
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { movieEntity ->
            holder.bind(movieEntity)
        }
    }
}