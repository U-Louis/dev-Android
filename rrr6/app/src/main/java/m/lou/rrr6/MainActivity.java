package m.lou.rrr6;

import androidx.appcompat.app.AppCompatActivity;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private GridView boardView;
    private GridViewAdapter boardGridAdapter;
    private GridView handView;
    private GridViewAdapter handGridAdapter;
    private GridView neutralsView;
    private GridViewAdapter neutralsGridAdapter;

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


        neutralsView = (GridView) findViewById(R.id.neutralsView);
        neutralsGridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, fillNeutralsGridAdapter());
        neutralsView.setAdapter(neutralsGridAdapter);

    }

    // Fill board with empty spots
    private ArrayList<ImageItem> fillBoardGridAdapter() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
        for (int i = 0; i < 9; i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(0, -1));
           imageItems.add(new ImageItem(bitmap/*, "Image#" + i*/));
        }
        return imageItems;
    }

    // Fill hand with starting cards
    private ArrayList<ImageItem> fillHandGridAdapter() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
        for (int i = 1; i < 9; i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            imageItems.add(new ImageItem(bitmap/*, "Image#" + i*/));
        }
        return imageItems;
    }

    // Fill neutrals with random neutral cards
    private ArrayList<ImageItem> fillNeutralsGridAdapter() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);

            //TODO : random neutral cards
            for (int i = 0; i < 5; i++) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(0, -1));
                imageItems.add(new ImageItem(bitmap/*, "Image#" + i*/));
            }

        return imageItems;
    }






}