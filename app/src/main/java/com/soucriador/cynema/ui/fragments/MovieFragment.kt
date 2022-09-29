package com.soucriador.cynema.ui.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.soucriador.cynema.R
import com.soucriador.cynema.model.Movie
import com.soucriador.cynema.model.SectionData
import com.soucriador.cynema.model.response.FeaturedImagesResponse
import com.soucriador.cynema.rest.ApiClient
import com.soucriador.cynema.ui.adapters.MovieAdapter
import com.soucriador.cynema.ui.adapters.RecyclerViewDataAdapter
import com.soucriador.cynema.ui.interfaces.ApiInterface
import com.soucriador.cynema.ui.interfaces.OnFragmentInteractionListener
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.TextSliderView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class MovieFragment : Fragment() {

    private var mListener: OnFragmentInteractionListener? = null
    private var MOVIES: ArrayList<Movie>? = ArrayList()
    private var MOVIES_SOON: ArrayList<Movie>? = ArrayList()
    private var FEATURED_IMAGES: FeaturedImagesResponse? = null

    private var topHeader: SliderLayout? = null
    private var allSampleData: ArrayList<SectionData>? = null
    private var itemsList: ArrayList<Movie>? = null
    private var itemsListSoon: ArrayList<Movie>? = null

    private var mAdapter: MovieAdapter? = null

    private var adapter: RecyclerViewDataAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_movie, container, false)

        allSampleData = ArrayList()

        val myRecyclerView: RecyclerView = view.findViewById(R.id.myRecyclerView)

        myRecyclerView.setHasFixedSize(true)
        adapter = RecyclerViewDataAdapter(view.context, allSampleData, mListener!!)
        myRecyclerView.setLayoutManager(LinearLayoutManager(view.context, RecyclerView.VERTICAL, false));
        myRecyclerView.setAdapter(adapter)

        itemsList = ArrayList()
        mAdapter = MovieAdapter(view.context, itemsList!!, mListener)
        itemsListSoon = ArrayList()
        populateMovies()

        topHeader = view.findViewById(R.id.topHeader)
        fetchSlides()
        return view
    }

    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * Converting dp to pixel
     */
    private fun dpToPx(dp: Int): Int {
        val r = resources
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics))
    }

    fun fetchSlides() {
        val call = apiService.featuredImages()
        call.enqueue(object : Callback<FeaturedImagesResponse> {
            override fun onResponse(call: Call<FeaturedImagesResponse>, response: Response<FeaturedImagesResponse>) {
                if (response.body() != null) {
                    FEATURED_IMAGES = response.body()

                    FEATURED_IMAGES!!.images?.forEachIndexed { index, featuredImage ->
                        val textSliderView = TextSliderView(context)
                        Log.e("ADICIONEI", featuredImage.image.toString())
                        textSliderView
                                .description(featuredImage.description)
                                .image(featuredImage.image)
                        topHeader!!.addSlider(textSliderView)
                    }
                }
            }

            override fun onFailure(call: Call<FeaturedImagesResponse>, t: Throwable) {
                Toast.makeText(context, R.string.failed_connection, Toast.LENGTH_SHORT).show()
                Log.e(CLASS_NAME, t.localizedMessage)
            }
        })
    }

    fun createDummyData() {
        for (i in 1..2) {

            val dm = SectionData()

            if (i == 1) {
                dm.headerTitle = "Em Exibição"
                dm.allItemsInSection = itemsList
            }

            if (i == 2) {
                dm.headerTitle = "Em Breve"
                dm.allItemsInSection = itemsListSoon
            }

            /* ArrayList<Movie> singleItem = new ArrayList<Movie>();
            for (int j = 0; j <= 5; j++) {
                singleItem.add(new Movie("Item " + j, "URL " + j));
            }*/

            allSampleData!!.add(dm)

        }
        adapter!!.notifyDataSetChanged()
    }

    private fun populateMovies() {
        val apiService = com.soucriador.cynema.rest.ApiClient.getClient().create(ApiInterface::class.java)
        val call = apiService.allMovies()
        call.enqueue(object : Callback<ArrayList<Movie>> {
            override fun onResponse(call: Call<ArrayList<Movie>>, response: Response<ArrayList<Movie>>) {
                if (response.body() != null) {
                    MOVIES = response.body()
                    itemsList!!.clear()
                    itemsList!!.addAll(MOVIES!!)
                    mAdapter!!.notifyDataSetChanged()
                    populateMoviesSoon()
                }
            }

            override fun onFailure(call: Call<ArrayList<Movie>>, t: Throwable) {
                Toast.makeText(context, R.string.failed_connection, Toast.LENGTH_SHORT).show()
                Log.e(CLASS_NAME, t.localizedMessage)
            }
        })
    }

    internal fun populateMoviesSoon() {
        val call = apiService.allMoviesSoon()
        call.enqueue(object : Callback<ArrayList<Movie>> {
            override fun onResponse(call: Call<ArrayList<Movie>>, response: Response<ArrayList<Movie>>) {
                if (response.body() != null) {
                    MOVIES_SOON = response.body()
                    itemsListSoon!!.clear()
                    itemsListSoon!!.addAll(MOVIES_SOON!!)
                    mAdapter!!.notifyDataSetChanged()
                    createDummyData()
                }
            }

            override fun onFailure(call: Call<ArrayList<Movie>>, t: Throwable) {
                Toast.makeText(context, R.string.failed_connection, Toast.LENGTH_SHORT).show()
                Log.e(CLASS_NAME, t.localizedMessage)
            }
        })
    }

    companion object {

        private val CLASS_NAME = MovieFragment::class.java.simpleName
        private lateinit var apiService: ApiInterface

        fun newInstance(): MovieFragment {
            apiService = com.soucriador.cynema.rest.ApiClient.getClient().create(ApiInterface::class.java)
            return MovieFragment()
        }
    }

}