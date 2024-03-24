package com.example.and102_week4

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.and102_week4.R

private const val TAG = "DetailActivity"

class DetailActivity : AppCompatActivity() {
    private lateinit var mediaImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var countryTextView: TextView
    private lateinit var summaryTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_screen)

        mediaImageView=findViewById(R.id.mediaPoster)
        titleTextView=findViewById(R.id.mediaTitle)
        countryTextView=findViewById(R.id.mediaSummary)
        summaryTextView=findViewById(R.id.mediaVote)
        // TODO: Get the extra from the Intent
        val movie=intent.getSerializableExtra(MOVIE_EXTRA) as TopRated
        // TODO: Set the title, byline, and abstract information from the article
        titleTextView.text = movie.nameMovie
        countryTextView.text= movie.country.toString() + " people are watching"
        summaryTextView.text = movie.summary
        // TODO: Load the media image
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500" + movie.movieUrl)
            .into(mediaImageView)
    }
}