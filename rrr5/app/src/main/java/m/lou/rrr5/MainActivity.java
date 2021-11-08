package m.lou.rrr5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements PopupMenu.OnMenuItemClickListener {

    public Card selectedCard;

    //BOARD
    ImageView b0;
    ImageView b1;
    ImageView b2;
    ImageView b3;
    ImageView b4;
    ImageView b5;
    ImageView b6;
    ImageView b7;
    ImageView b8;

    //P1 HAND
    static ImageView h0;
    static ImageView h1;
    static ImageView h2;
    static ImageView h3;
    static ImageView h4;
    static ImageView h5;
    static ImageView h6;
    static ImageView h7;

    Card [] ArenaCards = {};
    Card [] neutralsDeck = {};
    List<Card> p1HandCards = new ArrayList<Card>();
    Card [] p2HandCards = {};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initImageViews();
        initCards();

    }


    private void initImageViews() {
        this.b0 = findViewById(R.id.b0);
        this.b1 = findViewById(R.id.b1);
        this.b2 = findViewById(R.id.b2);
        this.b3 = findViewById(R.id.b3);
        this.b4 = findViewById(R.id.b4);
        this.b5 = findViewById(R.id.b5);
        this.b6 = findViewById(R.id.b6);
        this.b7 = findViewById(R.id.b7);
        this.b8 = findViewById(R.id.b8);

        this.h0 = findViewById(R.id.h0);
        this.h1 = findViewById(R.id.h1);
        this.h2 = findViewById(R.id.h2);
        this.h3 = findViewById(R.id.h3);
        this.h4 = findViewById(R.id.h4);
        this.h5 = findViewById(R.id.h5);
        this.h6 = findViewById(R.id.h6);
        this.h7 = findViewById(R.id.h7);

    //neutrals
    //p2
    }

    private void initCards() {
        p1HandCards.add(new Card("king",  "p1h0" ));
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