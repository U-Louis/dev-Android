package m.lou.rrr5;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.app.ListActivity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements PopupMenu.OnMenuItemClickListener {

    public View selectedCard;

/*
    //VIEW BOARD
    ImageView view_0board;
    ImageView view_1board;
    ImageView view_2board;
    ImageView view_3board;
    ImageView view_4board;
    ImageView view_5board;
    ImageView view_6board;
    ImageView view_7board;
    ImageView view_8board;

    //VIEW PLAYER HAND
    ImageView view_0player;
    ImageView view_1player;
    ImageView view_2player;
    ImageView view_3player;
    ImageView view_4player;
    ImageView view_5player;
    ImageView view_6player;
    ImageView view_7player;

    //VIEW NEUTRALS
    ImageView view_0neutrals;
    ImageView view_1neutrals;
    ImageView view_2neutrals;
    ImageView view_3neutrals;
    ImageView view_4neutrals;

    //DECKS
    List<Card> deck_board = new ArrayList<Card>();
    List<Card> deck_neutrals = new ArrayList<Card>();
    List<Card> deck_blue = new ArrayList<Card>();
    List<Card> deck_red = new ArrayList<Card>();
    List<Card> deck_discard = new ArrayList<Card>();
*/

    GridView boardGrid;
    ImageView prop_empty;
    String [] cardNames = new String[]{
            "empty",
            "king",
            "queen",
            "princess",
            "minister",
            "sorcerer",
            "general",
            "castle",
            "citizen"
    };

    //+++ON CREATE+++
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boardGrid = findViewById(R.id.gridView);
        prop_empty = findViewById(R.id.deck_0board);
Log.i("rerere", String.valueOf(prop_empty)+" "+ String.valueOf(R.id.emptySpot));

        final ArrayAdapter adapter = new ArrayAdapter(
                this, R.layout.prop_empty, R.id.emptySpot, cardNames );
        boardGrid.setAdapter(adapter);
/*
        initImageViews();
        initCards();
*/



/*        SpotAdapter adaptateur = new SpotAdapter(this,  cardNames);
        setListAdapter(adaptateur);*/
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(this, "Position : " + position, Toast.LENGTH_LONG).show();
    }

/*    private void initImageViews() {
        this.view_0board = findViewById(R.id.deck_0board);
        this.view_1board = findViewById(R.id.deck_1board);
        this.view_2board = findViewById(R.id.deck_2board);
        this.view_3board = findViewById(R.id.deck_3board);
        this.view_4board = findViewById(R.id.deck_4board);
        this.view_5board = findViewById(R.id.deck_5board);
        this.view_6board = findViewById(R.id.deck_6board);
        this.view_7board = findViewById(R.id.deck_7board);
        this.view_8board = findViewById(R.id.deck_8board);

        this.view_0player = findViewById(R.id.deck_0player);
        this.view_1player = findViewById(R.id.deck_1player);
        this.view_2player = findViewById(R.id.deck_2player);
        this.view_3player = findViewById(R.id.deck_3player);
        this.view_4player = findViewById(R.id.deck_4player);
        this.view_5player = findViewById(R.id.deck_5player);
        this.view_6player = findViewById(R.id.deck_6player);
        this.view_7player = findViewById(R.id.deck_7player);

        this.view_0neutrals = findViewById(R.id.deck_0neutrals);
        this.view_1neutrals = findViewById(R.id.deck_1neutrals);
        this.view_2neutrals = findViewById(R.id.deck_2neutrals);
        this.view_3neutrals = findViewById(R.id.deck_3neutrals);
        this.view_4neutrals = findViewById(R.id.deck_4neutrals);
    }*/

    private void initCards() {
/*
        deck_blue.add(new Card("king", "p1h0"));
*/
    }
    //---ON CREATE---


    //+++CARD MOVE+++
    public void selectCard(View view) {
        this.selectedCard = view;

        //String stringId = getStringId(this.selectedCard);
    }

    public void putCard(View view){
/*    this.view_0neutrals = this.deck_blue.get(0).getProp();
    view.setImageResource(this.deck_blue.get(0).getProp());*/
        //test
        //put it

/*String p =String.valueOf(r);
Toast.makeText(getApplicationContext(),p , Toast.LENGTH_LONG).show();*/
        //remove from hand

        // return;
    }


    /**
     * @return String ID
     */
    public String getStringId(View view) {
        if (view.getId() == View.NO_ID) {
            return "no ID";
        } else {
            return getResources().getResourceEntryName(view.getId());
        }
    }



    /*
    public Boolean checkIfEmpty(String id) {
        String tempFirstLetter = String.valueOf(id.charAt(6)); //1ere lettre de l'id = NOM de la table à parcourir
        checkIfExists(id, tempFirstLetter); //list name = 1 lettre != nom list => HTF to cast ?

        return false;
    }

    private void checkIfExists(String id, String deckToCheck) {

        List<Card> selectedList;
        switch (deckToCheck) {
            case "b":
                selectedList = deck_board;
            case "n":
                selectedList = deck_neutrals;
            case "p1":
                selectedList = deck_blue;
            case "p2":
                selectedList = deck_red;
            default:
                selectedList = deck_discard;
        }

        for (Card c : (List<Card>) selectedList) {

        }
    }

    *///---CARD MOVE---


    //+++MENU+++
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.newgame:
                //TODO
                Toast.makeText(this, "new game", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }

    public void showMenu(View v) {
        PopupMenu menu = new PopupMenu(this, v);
        menu.setOnMenuItemClickListener(this);
        menu.inflate(R.menu.mainmenu);
        menu.show();

    }
    //---MENU---
}