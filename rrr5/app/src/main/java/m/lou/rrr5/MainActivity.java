package m.lou.rrr5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    public Card selectedCard;

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
    };

    int p1HandCards[] = {
            R.drawable.citizen,
            R.drawable.king,
            R.drawable.queen,
            R.drawable.princess,
            R.drawable.minister,
            R.drawable.general,
            R.drawable.sorcerer,
            R.drawable.castle

    };

    int neutralsDeck[] = {
            R.drawable.citizen,
            R.drawable.citizen,
            R.drawable.citizen,
            R.drawable.citizen,
            R.drawable.citizen
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Arena_grid
            //init
/*
            Arena_grid = (GridView) findViewById(R.id.arena_grid);
*/

/*           //setup adapter
            CardGridAdapter arenaCardGridAdapter = new CardGridAdapter(getApplicationContext(), ArenaCards);
            Arena_grid.setAdapter(arenaCardGridAdapter);

            //event listener on each item of the grid
            Arena_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                }
            });*/

        //p1Hand_grid
            //init

            //setup adapter


        //neutrals_grid
            //init

            //setup adapter

    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.newgame:
                //TODO
                Toast.makeText(this, "new game", Toast.LENGTH_SHORT).show();
             return true;
            default:
                return false;
            }
    }

    public void showMenu(View v){
        PopupMenu menu = new PopupMenu(this, v);
        menu.setOnMenuItemClickListener(this);
        menu.inflate(R.menu.mainmenu);
        menu.show();

    }

}