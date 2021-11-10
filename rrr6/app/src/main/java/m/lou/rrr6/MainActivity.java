package m.lou.rrr6;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
    private final ArrayList<CardItem> boardCardItems = new ArrayList<>();
    private GridView boardView;
    private GridViewAdapter boardGridAdapter;

    private final ArrayList<CardItem> handCardItems = new ArrayList<>();
    private GridView handView;
    private GridViewAdapter handGridAdapter;

    private final ArrayList<CardItem> neutralCardItems = new ArrayList<>();
    private GridView neutralsView;
    private GridViewAdapter neutralsGridAdapter;

    public Integer selectedCard;

    Bitmap emptyCardImg;
    public CardItem defaultEmptyCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //generate adapters
        boardView = (GridView) findViewById(R.id.boardView);
        boardGridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, fillBoardGridAdapter());
        boardView.setAdapter(boardGridAdapter);
        boardView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                putCard(i);
            }
        });

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

        emptyCardImg = BitmapFactory.decodeResource(this.getResources(), R.drawable.image_0);
        defaultEmptyCard = new CardItem(emptyCardImg);

    }


    // Fill board with empty spots
    private ArrayList<CardItem> fillBoardGridAdapter() {
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
        for (int i = 0; i < 9; i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(0, -1));
            boardCardItems.add(new CardItem(bitmap/*, "Image#" + i*/));
        }
        return boardCardItems;
    }

    // Fill hand with starting cards
    private ArrayList<CardItem> fillHandGridAdapter() {
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
        for (int i = 1; i < 9; i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            handCardItems.add(new CardItem(bitmap/*, "Image#" + i*/));
        }
        return handCardItems;
    }

    // Fill neutrals with random neutral cards
    private ArrayList<CardItem> fillNeutralsGridAdapter() {
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
            //TODO : random neutral cards
            for (int i = 0; i < 5; i++) {
                @SuppressLint("ResourceType") Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(8, -1));
                neutralCardItems.add(new CardItem(bitmap/*, "Image#" + i*/));
            }
        return neutralCardItems;
    }

    //card actions
    public void setSelectedCard(View view, Integer i, AdapterView<?> adapterView) {
        this.selectedCard = i;
        //this.selectedCard = (CardItem) adapterView.getItemAtPosition(i);
//Toast.makeText(this, "hello", Toast.LENGTH_LONG).show();
//Log.i("log", "selected card : " +String.valueOf(selectedCard));

    }

    public void putCard(Integer i){
        if(this.selectedCard !=null){

//Log.i("log", "targeted card : " +String.valueOf(i));

            boardCardItems.set(i, handCardItems.get(this.selectedCard));
            handCardItems.set(this.selectedCard, defaultEmptyCard);
            handGridAdapter.notifyDataSetChanged();
            boardGridAdapter.notifyDataSetChanged();

            this.selectedCard = null;

//Log.i("log", "boardCardItems : " +String.valueOf(boardCardItems));
//Log.i("log", "handCardItems : " +String.valueOf(handCardItems));
        }

    }




}