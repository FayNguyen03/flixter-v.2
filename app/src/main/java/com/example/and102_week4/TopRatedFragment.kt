package com.example.and102_week4
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
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
class TopRatedFragment : Fragment(), OnListFragmentInteractionListener {
    private lateinit var recyclerView: RecyclerView
    private var scrollListener: EndlessRecyclerViewScrollListener? = null
    private lateinit var progressBar:ContentLoadingProgressBar
    private var moviesList = mutableListOf<TopRated>()

    var page:Int=1
    // private lateinit var adapter: MoviesRecyclerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_top_movie_list, container, false)
        progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        recyclerView = view.findViewById<View>(R.id.recyclerView) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = GridLayoutManager(context,1)

        Log.d("MoviesFragement","Initialize")
        updateAdapter(recyclerView,page,progressBar)
        scrollListener = object : EndlessRecyclerViewScrollListener(recyclerView.layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                loadNextDataFromApi()
            }
        }

        recyclerView.addOnScrollListener(scrollListener!!)
        return view
    }

    private fun updateAdapter(recyclerView: RecyclerView, page: Int = 1, progressBar: ContentLoadingProgressBar) {
        progressBar.show()

        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()
        val params: RequestParams = RequestParams()
        params["api_key"]= API_KEY
        params["page"]= page.toString()
        client[
                "https://api.themoviedb.org/3/movie/top_rated",
                params,
                object : JsonHttpResponseHandler()
                {

                    override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                        val resultsJSON:JSONArray=json.jsonObject.getJSONArray("results")
                        val moviesRawJSON:String=resultsJSON.toString()
                        val gson = Gson()
                        val arrayMovieType = object : TypeToken<List<TopRated>>() {}.type
                        val models : List<TopRated> = gson.fromJson(moviesRawJSON, arrayMovieType)
                        moviesList.addAll(models)
                        recyclerView.adapter = TopRatedRecyclerViewAdapter(moviesList, this@TopRatedFragment)
                        // Look for this in Logcat:
                        progressBar.hide()
                        Log.d("MoviesFragment", "response successful")
                    }


                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        errorResponse: String,
                        t: Throwable?
                    ) {
                        // The wait for a response is over
                        progressBar.hide()

                        // If the error is not null, log it!
                        t?.message?.let {
                            Log.e("MoviesFragment", errorResponse)
                        }
                    }
                }]

    }

    /*
     * When we click a movie
     * Override from OnListFragmentInteraction
     */
    override fun onItemClick(item: TopRated) {
        Toast.makeText(context, "Release date: " + item.date, Toast.LENGTH_SHORT).show()
    }


    override fun RequestParam(): Any {
        TODO("Not yet implemented")
    }

    private fun loadNextDataFromApi() {
        page += 1
        updateAdapter(recyclerView,page,progressBar)
    }


}