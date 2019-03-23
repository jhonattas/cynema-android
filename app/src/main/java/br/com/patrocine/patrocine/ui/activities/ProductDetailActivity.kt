package br.com.patrocine.patrocine.ui.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.patrocine.patrocine.BuildConfig
import br.com.patrocine.patrocine.R
import br.com.patrocine.patrocine.model.Pizza
import br.com.patrocine.patrocine.model.Snack
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_detail.*

class ProductDetailActivity : AppCompatActivity() {

    private val title: String? = null
    private val ingredientes: String? = null
    private val preco: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val b = intent.extras

        val title = resources.getString(R.string.app_name)
        setTitle(title)

        if (b!!.getString("type") == "PIZZAS") {
            val p = b.getSerializable("product") as Pizza
            tvProductTitle.text = "PIZZA " + p.title!!
            tvProductIngredients.text = p.ingredients
            tvProductValue.text = "R$ " + p.price!!
            val imageUrl = p.preview
        }

        if (b.getString("type") == "LANCHES") {
            val s = b.getSerializable("product") as Snack
            tvProductTitle.text = s.title
            tvProductIngredients.text = s.ingredients
            tvProductValue.text = "R$ " + s.price!!
            val imageUrl = s.preview

            Picasso.get().load(imageUrl).into(productImage)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_product_detail, menu)
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    override fun onBackPressed() {
        // finaliza a activity quando clicar em voltar
        finish()
    }
}
