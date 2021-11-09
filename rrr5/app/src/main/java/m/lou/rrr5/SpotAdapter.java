package m.lou.rrr5;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SpotAdapter extends ArrayAdapter<String> {

        private Integer[] tab_images_pour_la_liste = {
                R.drawable.empty,
                R.drawable.king,
                R.drawable.queen,
                R.drawable.princess,
                R.drawable.minister,
                R.drawable.sorcerer,
                R.drawable.general,
                R.drawable.castle,
                R.drawable.citizen,
};

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater)
                    getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View rowView = inflater.inflate(R.layout.prop_empty, parent, false);
Log.i("imgview", String.valueOf(rowView));
            //TextView textView = (TextView) rowView.findViewById(R.id.label);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.emptySpot);
 Log.i("imgview", String.valueOf(imageView));

            //textView.setText(getItem(position));

            if(convertView == null ){
 Log.i("imgview", String.valueOf(imageView));
                imageView.setImageResource(tab_images_pour_la_liste[position]);
            }
            else
                rowView = (View)convertView;

            return rowView;
        }

        public SpotAdapter(Context context, String[] values) {
            super(context, R.layout.prop_empty, values);
        }
    }
