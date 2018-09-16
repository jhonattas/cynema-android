package br.com.patrocine.patrocine.ui.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.patrocine.patrocine.R;
import br.com.patrocine.patrocine.model.Movie;
import br.com.patrocine.patrocine.model.SectionData;
import br.com.patrocine.patrocine.rest.ApiClient;
import br.com.patrocine.patrocine.ui.adapters.MovieAdapter;
import br.com.patrocine.patrocine.ui.adapters.RecyclerViewDataAdapter;
import br.com.patrocine.patrocine.ui.interfaces.ApiInterface;
import br.com.patrocine.patrocine.ui.interfaces.OnFragmentInteractionListener;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieFragment extends Fragment {

    private static final String CLASS_NAME = MovieFragment.class.getSimpleName();
    private OnFragmentInteractionListener mListener;
    private ArrayList<Movie> MOVIES = new ArrayList<>();
    private ArrayList<Movie> MOVIES_SOON = new ArrayList<>();

    private ImageView topHeader;
    private RecyclerView recyclerView;
    private ArrayList<SectionData> allSampleData;
    private ArrayList<Movie> itemsList;
    private ArrayList<Movie> itemsListSoon;

    private MovieAdapter mAdapter;

    private RecyclerViewDataAdapter adapter;

    public MovieFragment() {
    }

    public static MovieFragment newInstance() {
        MovieFragment fragment = new MovieFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_new, container, false);

        allSampleData = new ArrayList<>();

        RecyclerView my_recycler_view = view.findViewById(R.id.my_recycler_view);

        my_recycler_view.setHasFixedSize(true);
        adapter = new RecyclerViewDataAdapter(view.getContext(), allSampleData, mListener);
        my_recycler_view.setLayoutManager(new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false));
        my_recycler_view.setAdapter(adapter);

        recyclerView = view.findViewById(R.id.recycler_view);
        itemsList = new ArrayList<>();
        mAdapter = new MovieAdapter(getActivity(), itemsList, mListener);

        itemsListSoon = new ArrayList<>();
        //mAdapterSoon = new MovieSoonAdapter(getActivity(), itemsListSoon, mListener);

        /*
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(8), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        */

        populateMovies();
        topHeader = view.findViewById(R.id.topHeader);
        String url = "https://patrocine.com.br/static/img/welcome/slides/slider_01.png";
        Picasso.get().load(url).into(topHeader);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public void createDummyData() {
        for (int i = 1; i <= 2; i++) {

            SectionData dm = new SectionData();

            if(i == 1){
                dm.setHeaderTitle("Em Cartaz");
                dm.setAllItemsInSection(itemsList);
            }

            if(i == 2){
                dm.setHeaderTitle("Em Breve");
                dm.setAllItemsInSection(itemsListSoon);
            }

            /* ArrayList<Movie> singleItem = new ArrayList<Movie>();
            for (int j = 0; j <= 5; j++) {
                singleItem.add(new Movie("Item " + j, "URL " + j));
            }*/

            allSampleData.add(dm);

        }
        adapter.notifyDataSetChanged();
    }

    private void populateMovies(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<Movie>> call = apiService.getAllMovies();
        call.enqueue(new Callback<ArrayList<Movie>>() {
            @Override
            public void onResponse(Call<ArrayList<Movie>> call, Response<ArrayList<Movie>> response) {
                if(response.body() != null) {
                    MOVIES = response.body();
                    itemsList.clear();
                    itemsList.addAll(MOVIES);
                    mAdapter.notifyDataSetChanged();
                    populateMoviesSoon();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Movie>> call, Throwable t) {
                Toast.makeText(getContext(), R.string.failed_connection, Toast.LENGTH_SHORT).show();
                Log.e(CLASS_NAME, t.getLocalizedMessage());
            }
        });
    }

    void populateMoviesSoon(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<Movie>> call = apiService.getAllMoviesSoon();
        call.enqueue(new Callback<ArrayList<Movie>>() {
            @Override
            public void onResponse(Call<ArrayList<Movie>> call, Response<ArrayList<Movie>> response) {
                if(response.body() != null) {
                    MOVIES_SOON = response.body();
                    itemsListSoon.clear();
                    itemsListSoon.addAll(MOVIES_SOON);
                    mAdapter.notifyDataSetChanged();
                    createDummyData();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Movie>> call, Throwable t) {
                Toast.makeText(getContext(), R.string.failed_connection, Toast.LENGTH_SHORT).show();
                Log.e(CLASS_NAME, t.getLocalizedMessage());
            }
        });
    }

}
