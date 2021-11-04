package m.lou.rrr5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public Card selectedCard;

    GridView Arena_grid;
    int ArenaCards[] = {
            R.drawable.empty,
            R.drawable.empty,
            R.drawable.empty,
            R.drawable.empty,
            R.drawable.empty,
            R.drawable.empty,
            R.drawable.empty,
            R.drawable.empty,
            R.drawable.empty
            //...ArenaCards imgs
    };

    GridView p1Hand_grid;
    int p1HandCards[] = {
            R.drawable.citizen,
            R.drawable.citizen,
            R.drawable.citizen,
            R.drawable.citizen,
            R.drawable.citizen,
            R.drawable.citizen,
            R.drawable.citizen
    };

    GridView neutrals_grid;
    int neutralsDeck[] = {
            R.drawable.empty,
            R.drawable.empty,
            R.drawable.empty,
            R.drawable.empty,
            R.drawable.empty
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Arena_grid
            //init
            Arena_grid = (GridView) findViewById(R.id.arena_grid);

            //setup adapter
            CardGridAdapter arenaCardGridAdapter = new CardGridAdapter(getApplicationContext(), ArenaCards);
            Arena_grid.setAdapter(arenaCardGridAdapter);

            //event listener on each item of the grid
            Arena_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //TODO
                }
            });

        //p1Hand_grid
            //init
            p1Hand_grid = (GridView) findViewById(R.id.p1hand_grid);

            //setup adapter
            CardGridAdapter p1handCardGridAdapter = new CardGridAdapter(getApplicationContext(), p1HandCards);
            p1Hand_grid.setAdapter(p1handCardGridAdapter);

        //neutrals_grid
            //init
            neutrals_grid = (GridView) findViewById(R.id.neutrals_grid);

            //setup adapter
            CardGridAdapter neutralsGridAdapter = new CardGridAdapter(getApplicationContext(), neutralsDeck);
            neutrals_grid.setAdapter(neutralsGridAdapter);

    }

}