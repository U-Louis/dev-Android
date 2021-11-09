package m.lou.rrr5;

import static android.provider.Settings.System.getString;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.Toast;

public class Card  {

    private String name;
    private ImageView prop;
    private String position;

    public Card(String name, String position) {
        this.name = name;
        this.prop = prop;
        this.position = position;
    }

    /* SETTERS */
    public void setName(String name) {
        this.name = name;
    }

    public void setProp(ImageView prop) {
        switch (this.name) {
            case "king":
                this.prop.setImageResource(R.drawable.king);
                break;
            case "queen":
                this.prop.setImageResource(R.drawable.queen);
                break;

            default:
                this.prop.setImageResource(R.drawable.empty);
        }

    }

    public void setPosition(String position) {
        this.position = position;
    }

    /* GETTERS */
    public String getName() {
        return name;
    }

    public ImageView getProp() {
        return prop;
    }

    public String getPosition() {
        return position;
    }


}
