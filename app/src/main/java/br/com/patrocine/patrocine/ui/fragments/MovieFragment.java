package br.com.patrocine.patrocine.ui.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
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

    ArrayList<SectionData> allSampleData;

    private OnFragmentInteractionListener mListener;
    private ArrayList<Movie> MOVIES = new ArrayList<>();

    private RecyclerView recyclerView;
    private ArrayList<Movie> itemsList;
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
        adapter = new RecyclerViewDataAdapter(view.getContext(), allSampleData);
        my_recycler_view.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        my_recycler_view.setAdapter(adapter);

        recyclerView = view.findViewById(R.id.recycler_view);
        itemsList = new ArrayList<>();
        mAdapter = new MovieAdapter(getActivity(), itemsList, mListener);

        /*

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(8), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        */

        populateMovies();

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
            }

            if(i == 2){
                dm.setHeaderTitle("Em Breve");
            }


            /* ArrayList<Movie> singleItem = new ArrayList<Movie>();
            for (int j = 0; j <= 5; j++) {
                singleItem.add(new Movie("Item " + j, "URL " + j));
            }*/

            dm.setAllItemsInSection(itemsList);
            allSampleData.add(dm);

        }
        adapter.notifyDataSetChanged();
    }

    void populateMovies(){
        Log.e(CLASS_NAME, "entrei em POPULATE");

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
