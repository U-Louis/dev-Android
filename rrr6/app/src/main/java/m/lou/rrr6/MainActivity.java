package m.lou.rrr6;

import androidx.appcompat.app.AppCompatActivity;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private GridView boardView;
    private GridViewAdapter boardGridAdapter;
    private GridView handView;
    private GridViewAdapter handGridAdapter;
    private GridView neutralsView;
    private GridViewAdapter neutralsGridAdapter;

    public CardItem selectedCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //generate adapters
        boardView = (GridView) findViewById(R.id.boardView);
        boardGridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, fillBoardGridAdapter());
        boardView.setAdapter(boardGridAdapter);

        handView = (GridView) findViewById(R.id.handView);
        handGridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, fillHandGridAdapter());
        handView.setAdapter(handGridAdapter);
        handView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                setSelectedCard(view, i, adapterView);
            }
    });

        neutralsView = (GridView) findViewById(R.id.neutralsView);
        neutralsGridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, fillNeutralsGridAdapter());
        neutralsView.setAdapter(neutralsGridAdapter);

    }

    // Fill board with empty spots
    private ArrayList<CardItem> fillBoardGridAdapter() {
        final ArrayList<CardItem> cardItems = new ArrayList<>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
        for (int i = 0; i < 9; i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(0, -1));
           cardItems.add(new CardItem(bitmap/*, "Image#" + i*/));
        }
        return cardItems;
    }

    // Fill hand with starting cards
    private ArrayList<CardItem> fillHandGridAdapter() {
        final ArrayList<CardItem> cardItems = new ArrayList<>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
        for (int i = 1; i < 9; i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            cardItems.add(new CardItem(bitmap/*, "Image#" + i*/));
        }
        return cardItems;
    }

    // Fill neutrals with random neutral cards
    private ArrayList<CardItem> fillNeutralsGridAdapter() {
        final ArrayList<CardItem> cardItems = new ArrayList<>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);

            //TODO : random neutral cards
            for (int i = 0; i < 5; i++) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(8, -1));
                cardItems.add(new CardItem(bitmap/*, "Image#" + i*/));
            }

        return cardItems;
    }

    //card actions
    public void setSelectedCard(View view, Integer i, AdapterView<?> adapterView) {
        this.selectedCard = (CardItem) adapterView.getItemAtPosition(i);
Toast.makeText(this, "hello", Toast.LENGTH_LONG).show();
Log.i("log", String.valueOf(selectedCard));

    }




}