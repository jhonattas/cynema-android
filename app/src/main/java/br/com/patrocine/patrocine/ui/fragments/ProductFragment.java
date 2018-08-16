package br.com.patrocine.patrocine.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import br.com.patrocine.patrocine.R;
import br.com.patrocine.patrocine.model.Pizza;
import br.com.patrocine.patrocine.model.Snack;
import br.com.patrocine.patrocine.model.response.PizzaResponse;
import br.com.patrocine.patrocine.model.response.SnackResponse;
import br.com.patrocine.patrocine.rest.ApiClient;
import br.com.patrocine.patrocine.ui.adapters.PizzaDataAdapter;
import br.com.patrocine.patrocine.ui.adapters.SnackDataAdapter;
import br.com.patrocine.patrocine.ui.interfaces.ApiInterface;
import br.com.patrocine.patrocine.ui.interfaces.OnFragmentInteractionListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductFragment extends Fragment {

    private static final String CLASS_NAME = ProductFragment.class.getSimpleName();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;

    private RecyclerView mListView;
    private PizzaDataAdapter pizzaAdapter;
    private SnackDataAdapter snacksAdapter;
    private View view;
    private ArrayList<Pizza> PIZZAS = new ArrayList<>();
    private ArrayList<Snack> SNACKS = new ArrayList<>();
    private String category;
    private OnFragmentInteractionListener listner

            ;

    public static ProductFragment newInstance(String param1, String param2) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ProductFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString(ARG_PARAM1);
            Log.d("ProductFragment", "categoria: " + category);
            if(category.equals("PIZZAS")){
                populatePizzas();
            }

            if(category.equals("LANCHES")){
                populateSnacks();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pizza_list, container, false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listner = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listner = null;
    }

    private void populatePizzas(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<PizzaResponse> call = apiService.getAllPizzas();
        call.enqueue(new Callback<PizzaResponse>() {
            @Override
            public void onResponse(Call<PizzaResponse> call, Response<PizzaResponse> response) {
                if(response.body() != null) {
                    Toast.makeText(view.getContext(), "Atualizado com sucesso", Toast.LENGTH_SHORT).show();
                    PIZZAS = response.body().getResults();
                    updateList();
                }
            }

            @Override
            public void onFailure(Call<PizzaResponse> call, Throwable t) {
                Toast.makeText(view.getContext(), "Problema ao atualizar", Toast.LENGTH_SHORT).show();
                Log.e(CLASS_NAME, t.getLocalizedMessage());
            }
        });
    }

    private void populateSnacks(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<SnackResponse> call = apiService.getAllSnacks();
        call.enqueue(new Callback<SnackResponse>() {
            @Override
            public void onResponse(Call<SnackResponse> call, Response<SnackResponse> response) {
                if(response.body() != null) {
                    Toast.makeText(view.getContext(), "Atualizado com sucesso", Toast.LENGTH_SHORT).show();
                    SNACKS = response.body().getResults();
                    updateList();
                }
            }

            @Override
            public void onFailure(Call<SnackResponse> call, Throwable t) {
                Toast.makeText(view.getContext(), "Problema ao atualizar", Toast.LENGTH_SHORT).show();
                Log.e(CLASS_NAME, t.getLocalizedMessage());
            }
        });
    }

    void updateList(){
        // Set the adapter
        mListView = view.findViewById(R.id.card_recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mListView.setLayoutManager(llm);

        // Set OnItemClickListener so we can be notified on item clicks
        if(category.equals("PIZZAS")) {
            pizzaAdapter = new PizzaDataAdapter(PIZZAS, listner);
            mListView.setAdapter(pizzaAdapter);
        }

        // lanches
        if(category.equals("LANCHES")) {
            snacksAdapter = new SnackDataAdapter(SNACKS, listner);
            mListView.setAdapter(snacksAdapter);
        }
    }
}
