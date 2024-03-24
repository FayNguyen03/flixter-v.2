package com.example.and102_week4

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray

private const val API_KEY = "dfc03e09135b0c8ecde221167521e6c7"
class NowPlayingFragment: Fragment(), OnListFragmentInteractionListener {
    private lateinit var recyclerView: RecyclerView
    private var scrollListener: EndlessRecyclerViewScrollListener? = null
    private var moviesList = mutableListOf<NowPlayingMovie>()

    var page:Int=1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_top_movie_list, container, false)
        recyclerView = view.findViewById<View>(R.id.nowPlaying) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        Log.d("NowPlayingFragment","Initialize")
        updateAdapter(recyclerView,page)
        scrollListener = object : EndlessRecyclerViewScrollListener(recyclerView.layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                loadNextDataFromApi()
            }
        }

        recyclerView.addOnScrollListener(scrollListener!!)
        recyclerView.adapter = NowPlayingRecyclerViewAdapter(moviesList, this@NowPlayingFragment)
        return view
    }

    private fun updateAdapter(recyclerView: RecyclerView, page: Int = 1) {

        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()
        val params: RequestParams = RequestParams()
        params["api_key"]= API_KEY
        params["page"]= page.toString()
        client[
                "https://api.themoviedb.org/3/movie/now_playing?",
                params,
                object : JsonHttpResponseHandler()
                {

                    override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                        val resultsJSON:JSONArray=json.jsonObject.getJSONArray("results")
                        val moviesRawJSON:String=resultsJSON.toString()
                        val gson = Gson()
                        val arrayMovieType = object : TypeToken<List<NowPlayingMovie>>() {}.type
                        val models : List<NowPlayingMovie> = gson.fromJson(moviesRawJSON, arrayMovieType)
                        moviesList.addAll(models)
                        recyclerView.adapter = NowPlayingRecyclerViewAdapter(moviesList, this@NowPlayingFragment)
                        Log.d("NowPlayingFragment", "response successful")
                    }


                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        errorResponse: String,
                        t: Throwable?
                    ) {


                        // If the error is not null, log it!
                        t?.message?.let {
                            Log.e("MoviesFragment", errorResponse)
                        }
                    }
                }]

    }


    fun onItemClick(item: NowPlayingMovie) {
        Toast.makeText(context, "Release date: " + item.date, Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(item: TopRated) {
        TODO("Not yet implemented")
    }


    override fun RequestParam(): Any {
        TODO("Not yet implemented")
    }

    private fun loadNextDataFromApi() {
        page += 1
        updateAdapter(recyclerView,page)
    }


}


