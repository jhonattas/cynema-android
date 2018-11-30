package br.com.patrocine.patrocine.ui.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.patrocine.patrocine.R
import br.com.patrocine.patrocine.model.Movie
import br.com.patrocine.patrocine.model.SectionData
import br.com.patrocine.patrocine.rest.ApiClient
import br.com.patrocine.patrocine.ui.adapters.MovieAdapter
import br.com.patrocine.patrocine.ui.adapters.RecyclerViewDataAdapter
import br.com.patrocine.patrocine.ui.interfaces.ApiInterface
import br.com.patrocine.patrocine.ui.interfaces.OnFragmentInteractionListener
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


class MovieFragment : Fragment() {
    private var mListener: OnFragmentInteractionListener? = null
    private var MOVIES: ArrayList<Movie>? = ArrayList()
    private var MOVIES_SOON: ArrayList<Movie>? = ArrayList()

    private var topHeader: ImageView? = null
    private var recyclerView: RecyclerView? = null
    private var allSampleData: ArrayList<SectionData>? = null
    private var itemsList: ArrayList<Movie>? = null
    private var itemsListSoon: ArrayList<Movie>? = null

    private var mAdapter: MovieAdapter? = null

    private var adapter: RecyclerViewDataAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_movie_new, container, false)
        allSampleData = ArrayList()

        // myRecyclerView.setHasFixedSize(true)
        adapter = RecyclerViewDataAdapter(view.context, allSampleData, mListener!!)
        // myRecyclerView.setLayoutManager(LinearLayoutManager(view.context, RecyclerView.VERTICAL, false))
        // myRecyclerView.setAdapter(adapter)

        recyclerView = view.findViewById(R.id.recycler_view)
        itemsList = ArrayList()
        mAdapter = MovieAdapter(requireContext(), itemsList!!, mListener)

        itemsListSoon = ArrayList()
        //mAdapterSoon = new MovieSoonAdapter(getActivity(), itemsListSoon, mListener);

        /*
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(8), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        */

        populateMovies()
        topHeader = view.findViewById(R.id.topHeader)
        val url = "https://patrocine.com.br/static/img/welcome/slides/slider_01.png"
        Picasso.get().load(url).into(topHeader)

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

    fun createDummyData() {
        for (i in 1..2) {

            val dm = SectionData()

            if (i == 1) {
                dm.headerTitle = "Em Cartaz"
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
        val apiService = ApiClient.getClient().create(ApiInterface::class.java)
        val call = apiService.allMovies
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
        val apiService = ApiClient.getClient().create(ApiInterface::class.java)
        val call = apiService.allMoviesSoon
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

        fun newInstance(): MovieFragment {
            return MovieFragment()
        }
    }

}