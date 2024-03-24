package com.example.and102_week4

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.and102_week4.R.id
import java.io.Serializable
import androidx.core.util.Pair


const val MOVIE_EXTRA = "MOVIE_EXTRA"
private const val TAG = "TopRatedRecyclerViewAdapter"
class TopRatedRecyclerViewAdapter (
    private val movies: List<TopRated>,
    private val mListener: OnListFragmentInteractionListener?
)
    : RecyclerView.Adapter<TopRatedRecyclerViewAdapter.TopRatedViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_top_movie, parent, false)
        return TopRatedViewHolder(view)
    }

    inner class TopRatedViewHolder(val mView: View) : RecyclerView.ViewHolder(mView),View.OnClickListener {
        var mItem: TopRated? = null
        val mMovieTitle: TextView = mView.findViewById<View>(id.topTitle) as TextView
        val mMoviePoster: ImageView = mView.findViewById<ImageView>(id.topPoster) as ImageView
        val mMovieRate: TextView = mView.findViewById<TextView>(id.rate)

        init {
            mView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val movie = movies[position]

                val intent = Intent(mView.context, DetailActivity::class.java).apply {
                    putExtra(MOVIE_EXTRA, movie as Serializable)
                }

                // Assuming mMoviePoster and mMovieTitle are not null at this point
                val p1: Pair<View, String> = Pair(mMoviePoster!!, "photo")
                val p2: Pair<View, String> = Pair(mMovieTitle!!, "name")

                // Ensure context is an activity before proceeding
                (mView.context as? Activity)?.let { activity ->
                    val options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(activity, p1, p2)
                    activity.startActivity(intent, options.toBundle())
                }
            }
        }
    }
    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {
        val movie = movies[position]

        holder.mItem = movie
        holder.mMovieTitle.text = movie.nameMovie
        holder.mMovieRate.text=movie.vote.toString()

        holder.itemView.setOnLongClickListener {
            Toast.makeText(holder.itemView.context, "Vote " + movie.vote.toString(), Toast.LENGTH_LONG).show()
            true // Indicate that the long click was consumed
        }
        Glide.with(holder.mView)
            .load("https://image.tmdb.org/t/p/w500" + movie.movieUrl)
            .centerInside()
            .transform(RoundedCorners(60))
            .placeholder(R.drawable.loading)
            .error(R.drawable.not_found)
            .into(holder.mMoviePoster);
    }



    override fun getItemCount(): Int {
        return movies.size
    }
}
