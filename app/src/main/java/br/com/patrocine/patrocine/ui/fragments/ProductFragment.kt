package br.com.patrocine.patrocine.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.patrocine.patrocine.R
import br.com.patrocine.patrocine.model.Pizza
import br.com.patrocine.patrocine.model.Snack
import br.com.patrocine.patrocine.model.response.PizzaResponse
import br.com.patrocine.patrocine.model.response.SnackResponse
import br.com.patrocine.patrocine.rest.ApiClient
import br.com.patrocine.patrocine.ui.adapters.PizzaDataAdapter
import br.com.patrocine.patrocine.ui.adapters.SnackDataAdapter
import br.com.patrocine.patrocine.ui.interfaces.ApiInterface
import br.com.patrocine.patrocine.ui.interfaces.OnFragmentInteractionListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


class ProductFragment : Fragment() {

    private val mParam1: String? = null

    private var mListView: RecyclerView? = null
    private var pizzaAdapter: PizzaDataAdapter? = null
    private var snacksAdapter: SnackDataAdapter? = null
    private var PIZZAS: ArrayList<Pizza>? = ArrayList()
    private var SNACKS: ArrayList<Snack>? = ArrayList()
    private var category: String? = null
    private var listner: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            category = arguments!!.getString(ARG_PARAM1)
            Log.d("ProductFragment", "categoria: " + category!!)
            if (category == "PIZZAS") {
                populatePizzas()
            }

            if (category == "LANCHES") {
                populateSnacks()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        inflater.inflate(R.layout.fragment_pizza_list, container, false)
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listner = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listner = null
    }

    private fun populatePizzas() {
        val apiService = ApiClient.getClient().create(ApiInterface::class.java)
        val call = apiService.allPizzas
        call.enqueue(object : Callback<PizzaResponse> {
            override fun onResponse(call: Call<PizzaResponse>, response: Response<PizzaResponse>) {
                if (response.body() != null) {
                    Toast.makeText(view!!.context, "Atualizado com sucesso", Toast.LENGTH_SHORT).show()
                    PIZZAS = response.body()!!.results
                    updateList()
                }
            }

            override fun onFailure(call: Call<PizzaResponse>, t: Throwable) {
                Toast.makeText(view!!.context, "Problema ao atualizar", Toast.LENGTH_SHORT).show()
                Log.e(CLASS_NAME, t.localizedMessage)
            }
        })
    }

    private fun populateSnacks() {
        val apiService = ApiClient.getClient().create(ApiInterface::class.java)
        val call = apiService.allSnacks
        call.enqueue(object : Callback<SnackResponse> {
            override fun onResponse(call: Call<SnackResponse>, response: Response<SnackResponse>) {
                if (response.body() != null) {
                    Toast.makeText(view!!.context, "Atualizado com sucesso", Toast.LENGTH_SHORT).show()
                    SNACKS = response.body()!!.results
                    updateList()
                }
            }

            override fun onFailure(call: Call<SnackResponse>, t: Throwable) {
                Toast.makeText(view!!.context, "Problema ao atualizar", Toast.LENGTH_SHORT).show()
                Log.e(CLASS_NAME, t.localizedMessage)
            }
        })
    }

    internal fun updateList() {
        // Set the adapter
        mListView = view!!.findViewById(R.id.card_recycler_view)
        val llm = LinearLayoutManager(context)
        llm.orientation = RecyclerView.VERTICAL
        mListView!!.layoutManager = llm

        // Set OnItemClickListener so we can be notified on item clicks
        if (category == "PIZZAS") {
            pizzaAdapter = PizzaDataAdapter(PIZZAS!!, listner)
            mListView!!.adapter = pizzaAdapter
        }

        // lanches
        if (category == "LANCHES") {
            snacksAdapter = SnackDataAdapter(SNACKS, listner)
            mListView!!.adapter = snacksAdapter
        }
    }

    companion object {

        private val CLASS_NAME = ProductFragment::class.java.simpleName
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        fun newInstance(param1: String, param2: String): ProductFragment {
            val fragment = ProductFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}