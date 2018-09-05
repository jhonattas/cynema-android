package br.com.patrocine.patrocine.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import br.com.patrocine.patrocine.R;
import br.com.patrocine.patrocine.model.SectionData;
import br.com.patrocine.patrocine.model.SingleItem;
import br.com.patrocine.patrocine.ui.adapters.RecyclerViewDataAdapter;

public class MoviesTest extends AppCompatActivity {

    ArrayList<SectionData> allSampleData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_test);
        allSampleData = new ArrayList<SectionData>();

        setTitle("Teste Jean");
        createDummyData();


        RecyclerView my_recycler_view = findViewById(R.id.my_recycler_view);

        my_recycler_view.setHasFixedSize(true);
        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(this, allSampleData);
        my_recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        my_recycler_view.setAdapter(adapter);


    }

    public void createDummyData() {
        for (int i = 1; i <= 3; i++) {

            SectionData dm = new SectionData();

            if(i == 1){
                dm.setHeaderTitle("Em Cartaz");
            }

            if(i == 2){
                dm.setHeaderTitle("Em Breve");
            }

            if(i == 3){
                dm.setHeaderTitle("Patrocinadores");
            }

            ArrayList<SingleItem> singleItem = new ArrayList<SingleItem>();
            for (int j = 0; j <= 5; j++) {
                singleItem.add(new SingleItem("Item " + j, "URL " + j));
            }

            dm.setAllItemsInSection(singleItem);
            allSampleData.add(dm);

        }
    }

}
