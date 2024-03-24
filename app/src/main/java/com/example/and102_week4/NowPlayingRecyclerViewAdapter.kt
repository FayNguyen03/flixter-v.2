package com.example.and102_week4

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.and102_week4.R.id

class NowPlayingRecyclerViewAdapter(
    private val movies: List<NowPlayingMovie>,
    private val mListener: OnListFragmentInteractionListener?
)
    : RecyclerView.Adapter<NowPlayingRecyclerViewAdapter.NowPlayingViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_now_playing, parent, false)
        return NowPlayingViewHolder(view)
    }


    inner class NowPlayingViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: NowPlayingMovie? = null
        val mMovieTitle: TextView = mView.findViewById<View>(id.nowPlayingTitle) as TextView
        val mMoviePoster: ImageView=mView.findViewById<ImageView>(id.nowPlayingPoster) as ImageView

    }

    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        val movie = movies[position]

        holder.mItem = movie
        holder.mMovieTitle.text = movie.nameMovie

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
            .into(holder.mMoviePoster)
    }


    override fun getItemCount(): Int {
        return movies.size
    }
}