package br.com.patrocine.patrocine.ui.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import br.com.patrocine.patrocine.BuildConfig;
import br.com.patrocine.patrocine.R;
import br.com.patrocine.patrocine.model.Pizza;
import br.com.patrocine.patrocine.model.Snack;
import com.squareup.picasso.Picasso;

public class ProductDetailActivity extends AppCompatActivity {

    private String title;
    private String ingredientes;
    private String preco;

    ImageView productImage;
    TextView tvProductTitle;
    TextView tvProductIngredients;
    TextView tvProductValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        productImage = findViewById(R.id.productImage);
        tvProductTitle = findViewById(R.id.tvProductTitle);
        tvProductIngredients = findViewById(R.id.tvProductIngredients);
        tvProductValue = findViewById(R.id.tvProductValue);

        Bundle b = getIntent().getExtras();

        String title = getResources().getString(R.string.app_name);
        setTitle(title);

        if(b.getString("type").equals("PIZZAS")){
            Pizza p = (Pizza) b.getSerializable("product");
            tvProductTitle.setText("PIZZA " + p.getTitle());
            tvProductIngredients.setText(p.getIngredients());
            tvProductValue.setText("R$ "+ p.getPrice());
            String imageUrl = BuildConfig.img_server + "pizzas/" + p.getPreview();
        }

        if(b.getString("type").equals("LANCHES")){
            Snack s = (Snack) b.getSerializable("product");
            tvProductTitle.setText(s.getTitle());
            tvProductIngredients.setText(s.getIngredients());
            tvProductValue.setText("R$ "+ s.getPrice());
            String imageUrl = s.getPreview();

            // Picasso.with(this).load(imageUrl).into(productImage);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product_detail, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // finaliza a activity quando clicar em voltar
        finish();
    }
}
