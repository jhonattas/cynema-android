package br.com.soucriador.cynema.cynema.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.patrocine.cynema.R
import br.com.patrocine.cynema.model.Pizza
import br.com.patrocine.cynema.ui.interfaces.OnFragmentInteractionListener
import java.util.ArrayList


class PizzaDataAdapter(private val pizzas: ArrayList<Pizza>, private val listener: OnFragmentInteractionListener?) : RecyclerView.Adapter<PizzaDataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): PizzaDataAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_row, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: PizzaDataAdapter.ViewHolder, i: Int) {

        viewHolder.pizza = pizzas[i]
        viewHolder.tvName.text = pizzas[i].title
        val price = "R$ " + pizzas[i].price.toString()
        viewHolder.tvVersion.text = price
        viewHolder.tvApiLevel.text = pizzas[i].ingredients

        viewHolder.mView.setOnClickListener {
            listener?.onFragmentInteraction(viewHolder.pizza!!)
        }
    }

    override fun getItemCount(): Int {
        return pizzas.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var pizza: Pizza? = null
        var tvName: TextView = mView.findViewById(R.id.tv_name)
        var tvVersion: TextView = mView.findViewById(R.id.tv_version)
        var tvApiLevel: TextView = mView.findViewById(R.id.tv_api_level)
    }

}