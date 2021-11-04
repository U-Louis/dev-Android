package m.lou.rrr5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class CardGridAdapter extends BaseAdapter {
    Context context;
    int cards[];
    LayoutInflater inflater;

    //CONSTRUCTOR
    public CardGridAdapter(Context applicationContext, int[] cards) {
        this.context = applicationContext;
        this.cards = cards;
        inflater = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return cards.length;
    }
    @Override
    public Object getItem(int i) {
        return null;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.card_prop, null); // inflate the layout
        ImageView icon = (ImageView) view.findViewById(R.id.card); // get the reference of ImageView
        icon.setImageResource(cards[i]); // set logo images
        return view;
    }


    }
